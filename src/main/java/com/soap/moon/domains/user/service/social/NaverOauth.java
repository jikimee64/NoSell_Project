package com.soap.moon.domains.user.service.social;

import com.soap.moon.domains.user.dto.AuthDto;
import com.soap.moon.domains.user.dto.AuthDto.AccessTokenReq;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class NaverOauth implements SocialOauth {

    @Value("${sns.naver.url}")
    private String NAVER_SNS_BASE_URL;

    @Value("${sns.naver.client.id}")
    private String NAVER_SNS_CLIENT_ID;

    @Value("${sns.naver.client.secret}")
    private String NAVER_SNS_CLIENT_SECRET;

    @Value("${sns.naver.callback.url}")
    private String NAVER_SNS_CALLBACK_URL;

    @Value("${sns.naver.token.url}")
    private String NAVER_SNS_TOKEN_BASE_URL;

    @Value("${sns.naver.userinfo.url}")
    private String NAVER_SNS_USERINFO_URL;

    private final RestTemplate restTemplate;

//    @Override
//    public String getOauthRedirectURL() {
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("client_id", NAVER_SNS_CLIENT_ID);
//        params.put("response_type", "code");
//        params.put("redirect_uri", NAVER_SNS_CALLBACK_URL);
//        params.put("state", generateRandomString());
//
//        String parameterString = params.entrySet().stream()
//            .map(x -> x.getKey() + "=" + x.getValue())
//            .collect(Collectors.joining("&"));
//
//        return NAVER_SNS_BASE_URL + "?" + parameterString;
//
//    }

//    @Override
//    public AuthDto.TokenRes requestAccessToken(String code, String state) {
//        Map<String, Object> params = new HashMap<>();
//
//        params.put("client_id", NAVER_SNS_CLIENT_ID);
//        params.put("client_secret", NAVER_SNS_CLIENT_SECRET);
//        params.put("grant_type", "authorization_code");
//        params.put("state", state);
//        params.put("code", code);
//
//        String parameterString = params.entrySet().stream()
//            .map(x -> x.getKey() + "=" + x.getValue())
//            .collect(Collectors.joining("&"));
//
//        try {
//            ResponseEntity<AuthDto.TokenRes> responseEntity =
//                restTemplate
//                    .getForEntity(NAVER_SNS_TOKEN_BASE_URL+"?"+parameterString, AuthDto.TokenRes.class);
//            if (responseEntity.getStatusCode() == HttpStatus.OK) {
//                log.info("responseEntity.getBody " + responseEntity.getBody());
//                return responseEntity.getBody();
//            }
//        } catch (HttpClientErrorException e) {
//            log.info("========== requestAccessToken() 네이버 API 인증 실패 에러 ==========");
//            e.printStackTrace();
//            //throw new NaverApiUnauthorizedException();
//        } catch (Exception e) {
//            log.info("========== requestAccessToken : 네이버 API 통신 알수 없는 에러 ==========");
//            e.printStackTrace();
//            //throw new NavereApiErrorException();
//        }
//        return null;
//
//    }

    @Override
    public AuthDto.NaverProfileRes userInfoNaver(AccessTokenReq dto) {
//        Map<String, Object> params = new HashMap<>();
//        System.out.println("access_token : " + tokenRes.getAccess_token());
//        params.put("access_token", tokenRes.getAccess_token());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + dto.getAccessToken());

        log.info("네이버 액세스 토큰 : " + dto.getAccessToken());
        try {
            ResponseEntity<AuthDto.NaverProfileRes> responseEntity =
                restTemplate
                    .exchange(NAVER_SNS_USERINFO_URL, HttpMethod.GET, new HttpEntity(httpHeaders),
                        AuthDto.NaverProfileRes.class);

//            ResponseEntity<GoogleAuthDto.GoogleProfileRes> responseEntity =
//                restTemplate.postForEntity(GOOGLE_SNS_USERINFO_URL, params,
//                    GoogleAuthDto.GoogleProfileRes.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                log.info("네이버 정보 : " + responseEntity.getBody());
                return responseEntity.getBody();
            }
        } catch (HttpClientErrorException e) {
            log.info("========== userInfoNaver() 네이버 API 인증 실패 에러 ==========");
            e.printStackTrace();
            //throw new NaverApiUnauthorizedException();
        } catch (Exception e) {
            log.info("========== userInfoNaver : 네이버 API 통신 알수 없는 에러 ==========");
            e.printStackTrace();
            //throw new NavereApiErrorException();
        }
        return null;
    }

    private String generateRandomString() {
        return UUID.randomUUID().toString();
    }
}