package com.soap.moon.domains.user.service;

import com.soap.moon.domains.user.domain.Account;
import com.soap.moon.domains.user.domain.Authority;
import com.soap.moon.domains.user.domain.Password;
import com.soap.moon.domains.user.domain.ProviderType;
import com.soap.moon.domains.user.domain.Token;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.domain.UserAuthority;
import com.soap.moon.domains.user.domain.UserOauth;
import com.soap.moon.domains.user.domain.UserStatus;
import com.soap.moon.domains.user.dto.AuthDto;
import com.soap.moon.domains.user.dto.AuthDto.AccessTokenReq;
import com.soap.moon.domains.user.dto.AuthDto.NaverProfileRes;
import com.soap.moon.domains.user.exception.MemberDuplicationException;
import com.soap.moon.domains.user.repository.AuthorityRepository;
import com.soap.moon.domains.user.repository.UserOauthRepository;
import com.soap.moon.domains.user.repository.UserRepository;
import com.soap.moon.domains.user.service.social.SocialOauth;
import com.soap.moon.global.jwt.JwtTokenProvider;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class OauthService {

    //GoogleOauth, NaverOatuh 삽입
    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;
    private final UserOauthRepository userOauthRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

//    public void request(ProviderType providerType) {
//        SocialOauth socialOauth = this.findSocialOauthByType(providerType);
//        String redirectURL = socialOauth.getOauthRedirectURL();
//        log.info("URL : " + redirectURL);
//        try {
//            response.sendRedirect(redirectURL);
//        } catch (IOException e) {
//            log.info("request error : " + redirectURL);
//            e.printStackTrace();
//        }
//    }

    public Map<String, Object> requestAccessToken(ProviderType providerType, AccessTokenReq dto) {
        SocialOauth socialOauth = this.findSocialOauthByType(providerType);
        //state 인자는 naver에서만 필요..(리펙토링 절실)
//        AuthDto.TokenRes tokenRes = socialOauth.requestAccessToken(code, state);
//
        String email = null;
        String providerId = null;

        if ("NAVER".equals(providerType.getName())) {
            NaverProfileRes naverProfileRes = socialOauth.userInfoNaver(dto);
            email = naverProfileRes.getResponse().getEmail();
            providerId = naverProfileRes.getResponse().getId();
        } else {
            AuthDto.GoogleProfileRes profileRes = socialOauth.userInfoGoogle(dto);
            email = profileRes.getEmail();
            providerId = profileRes.getId();
        }

        Account account = Account.builder().email(email).build();

        //ROLE_USER GET
        Optional<Authority> authorityRoleUser = authorityRepository.findById(1L);

        String finalProviderId = providerId;

        User user = userRepository.findByAccount(account).orElseGet(
            () -> {
                log.info("구글/네이버 정보 새로 DB 저장");
                Password password = null;

                if ("NAVER".equals(providerType.getName())) {
                    password = Password.builder()
                        .password(passwordEncoder.encode(ProviderType.NAVER.getName()))
                        .build();
                } else {
                    password = Password.builder()
                        .password(passwordEncoder.encode(ProviderType.GOOGLE.getName()))
                        .build();
                }

                User signUser = User.builder()
                    .account(account)
                    .password(password)
                    .nickName("")
                    .phoneNum("")
                    .status(UserStatus.ACTIVE)
                    .profileImage("")
                    .build();

                UserAuthority userAuthority = UserAuthority.builder()
                    .authority(authorityRoleUser.get())
                    .user(signUser)
                    .build();

                signUser.addAuthority(userAuthority);

                userRepository.save(
                    signUser
                );

                userOauthRepository.save(
                    UserOauth.builder()
                        .user(signUser)
                        .providerType(providerType)
                        .providerId(finalProviderId)
                        .build()
                );
                return signUser;
            }
        );

//        if (!userRepository.findByAccount(account).isPresent()) {
//        }

        //기존에 소셜 계정을 통해 가입했다면
//        userOauthRepository.findByUser(user).ifPresent(ofm -> {
//            log.info("ofm : " + ofm);
//            if (ProviderType.GOOGLE.getName().equals(ofm.getProviderType())) {
//                throw new SocialMemberDuplicationException(ProviderType.GOOGLE.getName());
//            } else if (ProviderType.NAVER.getName().equals(ofm.getProviderType())) {
//                throw new SocialMemberDuplicationException(ProviderType.NAVER.getName());
//            }
//        });

        userOauthRepository.findByUser(user).ifPresentOrElse(
            ofm -> {
                if (ProviderType.GOOGLE.getName().equals(ofm.getProviderType())) {
                    throw new MemberDuplicationException(ProviderType.GOOGLE.getName());
                } else if (ProviderType.NAVER.getName().equals(ofm.getProviderType())) {
                    throw new MemberDuplicationException(ProviderType.NAVER.getName());
                }
            },
            () -> {
                if(!StringUtils.isEmpty(user))
                    throw new MemberDuplicationException("자체");
            }
        );

        Map<String, Object> map = new HashMap<>();

        //아이디와 패스워드를 조합해서 인스턴스 생성
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(user.getAccount().getEmail(),
                providerType.getName());

        // authenticate() 실행시 loadUserByUsername 실행
        // 사용자 비밀번호 체크, 패스워드 일치하지 않는다면 Exception 발생 및 이후 로직 실행 안됨
        Authentication authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);

        //로그인 성공하면 인증 객체 생성 및 스프링 시큐리티 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);

        map.put(Token.ACCESS_TOKEN.getName(), jwtTokenProvider.doGenerateToken(authentication));
        map.put(Token.REFRESH_TOKEN.getName(),
            jwtTokenProvider.doGenerateRefreshToken(user.getAccount().getEmail()));

        Optional<User> byAccount = userRepository.findByAccount(account);
        byAccount.ifPresent( v -> {
            map.put("nickName", v.getNickName());
            map.put("profileImage", v.getProfileImage());
        });

        return map;
    }


    private SocialOauth findSocialOauthByType(ProviderType providerType) {
        return socialOauthList.stream()
            .filter(x -> x.type() == providerType)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }

}
