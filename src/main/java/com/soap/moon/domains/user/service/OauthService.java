package com.soap.moon.domains.user.service;

import com.soap.moon.domains.user.domain.SocialLoginType;
import com.soap.moon.domains.user.domain.UserOauth;
import com.soap.moon.domains.user.dto.GoogleAuthDto;
import com.soap.moon.domains.user.dto.GoogleAuthDto.GoogleProfileRes;
import com.soap.moon.domains.user.repository.UserOauthRepository;
import com.soap.moon.domains.user.service.social.SocialOauth;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OauthService {

    //GoogleOauth, NaverOatuh 삽입
    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;
    private final UserOauthRepository userOauthRepository;

    public void request(SocialLoginType socialLoginType) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String requestAccessToken(SocialLoginType socialLoginType, String code) {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        GoogleAuthDto.TokenRes tokenRes = socialOauth.requestAccessToken(code);
        GoogleProfileRes googleProfileRes = socialOauth.googleUserInfo(tokenRes);

        String email = googleProfileRes.getEmail();

        //null이면 DB저장,
        UserOauth userOauth = userOauthRepository.findByEmail(email).orElseGet(() -> {
            log.info("구글 정보 새로 DB 저장");
            return userOauthRepository.save(
                UserOauth.builder()
                    .access_token(tokenRes.getAccess_token())
                    .email(email)
                    .expireTime(tokenRes.getExpires_in())
                    .imageUrl(googleProfileRes.getPicture())
                    .socialLoginType(SocialLoginType.GOOGLE)
                    .build()
            );
        });
        return userOauth.getEmail();
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
