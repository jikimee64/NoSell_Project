package com.soap.moon.domains.user.service.social;

import com.soap.moon.domains.user.dto.AuthDto;
import com.soap.moon.domains.user.dto.AuthDto.TokenRes;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth {

    @Value("${sns.google.url}")
    private String GOOGLE_SNS_BASE_URL;

    @Value("${sns.google.client.id}")
    private String GOOGLE_SNS_CLIENT_ID;

    @Value("${sns.google.callback.url}")
    private String GOOGLE_SNS_CALLBACK_URL;

    @Value("${sns.google.client.secret}")
    private String GOOGLE_SNS_CLIENT_SECRET;

    @Value("${sns.google.token.url}")
    private String GOOGLE_SNS_TOKEN_BASE_URL;

    @Value("${sns.google.userinfo.url}")
    private String GOOGLE_SNS_USERINFO_URL;

    private final RestTemplate restTemplate;

    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", "profile email openid");
        params.put("response_type", "code");
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
        params.put("access_type", "offline");

        String parameterString = params.entrySet().stream()
            .map(x -> x.getKey() + "=" + x.getValue())
            .collect(Collectors.joining("&"));

        return GOOGLE_SNS_BASE_URL + "?" + parameterString;
    }

    @Override
    public AuthDto.TokenRes requestAccessToken(String code, String state) {

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
        params.put("grant_type", "authorization_code");
        try {
            ResponseEntity<AuthDto.TokenRes> responseEntity =
                restTemplate
                    .postForEntity(GOOGLE_SNS_TOKEN_BASE_URL, params, AuthDto.TokenRes.class);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            }
        } catch (HttpClientErrorException e) {
            log.info("========== 구글 API 인증 실패 에러 ==========");
            e.printStackTrace();
            //throw new NaverApiUnauthorizedException();
        } catch (Exception e) {
            log.info("========== requestAccessToken : 구글 API 통신 알수 없는 에러 ==========");
            e.printStackTrace();
            //throw new NavereApiErrorException();
        }
        return null;
    }

    @Override
    public AuthDto.GoogleProfileRes userInfoGoogle(TokenRes tokenRes) {
//        Map<String, Object> params = new HashMap<>();
//        System.out.println("access_token : " + tokenRes.getAccess_token());
//        params.put("access_token", tokenRes.getAccess_token());
        try {
            ResponseEntity<AuthDto.GoogleProfileRes> responseEntity =
                restTemplate.getForEntity(GOOGLE_SNS_USERINFO_URL + "/?access_token=" + tokenRes.getAccess_token(),
                    AuthDto.GoogleProfileRes.class);

//            ResponseEntity<GoogleAuthDto.GoogleProfileRes> responseEntity =
//                restTemplate.postForEntity(GOOGLE_SNS_USERINFO_URL, params,
//                    GoogleAuthDto.GoogleProfileRes.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return responseEntity.getBody();
            }
        } catch (HttpClientErrorException e) {
            log.info("========== 구글 API 인증 실패 에러 ==========");
            e.printStackTrace();
            //throw new NaverApiUnauthorizedException();
        } catch (Exception e) {
            log.info("========== userInfoGoogle : 구글 API 통신 알수 없는 에러 ==========");
            e.printStackTrace();
            //throw new NavereApiErrorException();
        }
        return null;
    }

}