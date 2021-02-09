package com.soap.moon.domains.user.service;

import com.soap.moon.domains.user.domain.Account;
import com.soap.moon.domains.user.domain.Authority;
import com.soap.moon.domains.user.domain.Password;
import com.soap.moon.domains.user.domain.SocialLoginType;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.domain.UserAuthority;
import com.soap.moon.domains.user.domain.UserOauth;
import com.soap.moon.domains.user.domain.UserStatus;
import com.soap.moon.domains.user.dto.AuthDto;
import com.soap.moon.domains.user.dto.AuthDto.NaverProfileRes;
import com.soap.moon.domains.user.dto.AuthDto.NaverProfileRes.Response;
import com.soap.moon.domains.user.repository.AuthorityRepository;
import com.soap.moon.domains.user.repository.UserOauthRepository;
import com.soap.moon.domains.user.repository.UserRepository;
import com.soap.moon.domains.user.service.social.SocialOauth;
import com.soap.moon.global.jwt.JwtTokenProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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

    public void request(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();
        log.info("URL : " + redirectURL);
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            log.info("request error : " + redirectURL);
            e.printStackTrace();
        }
    }

    public String requestAccessToken(SocialLoginType socialLoginType, String code, String state) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        //state 인자는 naver에서만 필요..(리펙토링 절실)
        AuthDto.TokenRes tokenRes = socialOauth.requestAccessToken(code, state);

        String email = null;

        if("NAVER".equals(socialLoginType.getName())){
            NaverProfileRes naverProfileRes = socialOauth.userInfoNaver(tokenRes);
            email = naverProfileRes.getResponse().getEmail();

        }else{
            AuthDto.GoogleProfileRes profileRes = socialOauth.userInfoGoogle(tokenRes);
            email = profileRes.getEmail();
        }

        Account account = Account.builder().email(email).build();

        //ROLE_USER GET
        Optional<Authority> authorityRoleUser = authorityRepository.findById(1L);

        //비 가입자면 DB저장,
        User user = userRepository.findByAccount(account).orElseGet(() -> {
            log.info("구글/네이버 정보 새로 DB 저장");
            Password password = null;

            if("NAVER".equals(socialLoginType.getName())){
                password = Password.builder()
                    .password(passwordEncoder.encode(SocialLoginType.NAVER.getName()))
                    .build();
            }else{
            }   password = Password.builder()
                .password(passwordEncoder.encode(SocialLoginType.GOOGLE.getName()))
                .build();

            User signUser = User.builder()
                .account(account)
                .password(password)
                .nickName("")
                .phoneNum("")
                .status(UserStatus.ACTIVE)
                .lastLoginAt(LocalDateTime.now())
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
                    .socialLoginType(SocialLoginType.GOOGLE)
                    .build()
            );
            return signUser;
        });

        //아이디와 패스워드를 조합해서 인스턴스 생성
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(user.getAccount().getEmail(), SocialLoginType.GOOGLE.getName());

        // authenticate() 실행시 loadUserByUsername 실행
        // 사용자 비밀번호 체크, 패스워드 일치하지 않는다면 Exception 발생 및 이후 로직 실행 안됨
        Authentication authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);

        //로그인 성공하면 인증 객체 생성 및 스프링 시큐리티 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.createToken(authentication);
    }


    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
            .filter(x -> x.type() == socialLoginType)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }

//    private AuthDto.TokenRes jsonParser(String res) {
//        AuthDto.TokenRes tokenRes = null;
//        try {
//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(res);
//            JSONObject jsonObj = (JSONObject) obj;
//
//            int expires_in = Integer.parseInt((String) jsonObj.get("expires_in"));
//            String access_token = (String) jsonObj.get("access_token");
//            String refresh_token = (String) jsonObj.get("refresh_token");
//
//            tokenRes = AuthDto.TokenRes.builder()
//                .access_token(access_token)
//                .refresh_token(refresh_token)
//                .expires_in(expires_in)
//                .build();
//        } catch (Exception e) {
//            log.info("OauthService - jsonParser : parse 에러");
//        }
//        return tokenRes;
//    }


}
