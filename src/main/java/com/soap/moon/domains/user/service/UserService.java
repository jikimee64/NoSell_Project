package com.soap.moon.domains.user.service;

import com.soap.moon.domains.user.domain.Account;
import com.soap.moon.domains.user.domain.Authority;
import com.soap.moon.domains.user.domain.ProviderType;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.domain.UserAuthority;
import com.soap.moon.domains.user.domain.UserStatus;
import com.soap.moon.domains.user.domain.Password;
import com.soap.moon.domains.user.dto.UserDto;
import com.soap.moon.domains.user.dto.UserDto.CheckUserAuthRes;
import com.soap.moon.domains.user.exception.JwtExpiredException;
import com.soap.moon.domains.user.exception.JwtMalFormedException;
import com.soap.moon.domains.user.exception.JwtUnsupportedException;
import com.soap.moon.domains.user.exception.MemberDuplicationException;
import com.soap.moon.domains.user.exception.MemberNotFoundException;
import com.soap.moon.domains.user.repository.AuthorityRepository;
import com.soap.moon.domains.user.repository.UserOauthRepository;
import com.soap.moon.domains.user.repository.UserRepository;
import com.soap.moon.global.jwt.JwtTokenProvider;
import com.soap.moon.global.util.SecurityUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import java.util.HashMap;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserOauthRepository userOauthRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${sms.apiKey}")
    private String apiKey;

    @Value("${sms.apiSecret}")
    private String apiSecret;

    /**
     * 회원가입
     */
    @Transactional
    public User save(UserDto.SignInReq dto) {
        validateDuplicateMember(dto.getEmail());

        Account account = Account.builder().email(dto.getEmail()).build();
        Password password = Password.builder()
            .password(passwordEncoder.encode(dto.getPassword()))
            .build();

        //ROLE_USER GET
        Optional<Authority> authorityRoleUser = authorityRepository.findById(1L);

        User user = User.builder()
            .account(account)
            .password(password)
            .nickName(dto.getNickName())
            .phoneNum(dto.getPhoneNum())
            .profileImage(
                "https://user-images.githubusercontent.com/52563841/108304539-9a01e200-71eb-11eb-94a7-01ead35e186e.png")
            .status(UserStatus.ACTIVE)
            .build();

        UserAuthority userAuthorityEntity = UserAuthority.builder()
            .user(user)
            .authority(authorityRoleUser.get())
            .build();

        user.addAuthority(userAuthorityEntity);
        return userRepository.save(user);
    }

    /**
     * 중복회원 체크 account컬럼 유니크 제약조건 추가
     */
    public boolean validateDuplicateMember(String email) {
        Optional<User> findMember = userRepository.findByAccount(getAccountByUserId(email));
        findMember.ifPresent(fm -> {
//            String exEmail = fm.getAccount().getEmail();
//            int idx = exEmail.indexOf("@");
//            String socialType = exEmail.substring(idx + 1);

            userOauthRepository.findByUser(fm).ifPresent(ofm -> {
                log.info("ofm.getProviderType()" + ofm.getProviderType());
                if ("GOOGLE".equals(ofm.getProviderType().getName())) {
                    throw new MemberDuplicationException(ProviderType.GOOGLE.getName());
                } else if ("NAVER".equals(ofm.getProviderType().getName())) {
                    throw new MemberDuplicationException(ProviderType.NAVER.getName());
                }
            });
            throw new MemberDuplicationException("자체");
        });
        return true;
    }

    public UserDto.CheckUserAuthRes checkUserAuth(String token) {
        Boolean flag = null;
        Authentication authentication = null;
        CheckUserAuthRes build = null;
        try {
            flag = jwtTokenProvider.validateExceptionToken(token);
            authentication = jwtTokenProvider.getAuthentication(token);
        } catch (SecurityException | MalformedJwtException ex) {
            throw new JwtMalFormedException();
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException();
        } catch (UnsupportedJwtException e) {
            throw new JwtUnsupportedException();
        }

        if (flag) {
            Account account = Account.builder().email(authentication.getName()).build();
            Optional<User> byAccount = userRepository.findByAccount(account);

            User user = null;
            if (!StringUtils.isEmpty(byAccount)) {
                user = byAccount.get();
                build = CheckUserAuthRes.builder()
                    .nickName(user.getNickName())
                    .profileImage(user.getProfileImage())
                    .build();
            } else {
                throw new MemberNotFoundException();
            }
        }
        return build;

    }

    public boolean certifiedPhoneNumber(String phoneNumber, String cerNum) {

        Message coolsms = new Message(apiKey, apiSecret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber);    // 수신전화번호
        params.put("from", "01023160200");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "NoSell 마켓 휴대폰인증 메시지 : 인증번호는" + "[" + cerNum + "]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
            return false;
        }
        return true;
    }

//    public Optional<User> getUserWithAuthorities(String userId) {
//        return userRepository.findOneWithAuthoritiesByAccount(userId);
//    }
//
//    public Optional<User> getMyUserWithAuthorities() {
//        return SecurityUtil.getCurrentUsername()
//            .flatMap(s -> userRepository.findOneWithAuthoritiesByAccount(s));
//    }

    private Account getAccountByUserId(String userId) {
        return Account.builder().email(userId).build();
    }


}
