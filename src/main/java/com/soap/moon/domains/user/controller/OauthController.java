package com.soap.moon.domains.user.controller;

import com.soap.moon.domains.user.domain.SocialLoginType;
import com.soap.moon.domains.user.dto.JwtTokenDto.TokenInRes;
import com.soap.moon.domains.user.service.OauthService;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.global.jwt.JwtFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"5. OAuth"}, value = "소셜 로그인")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
@Slf4j
public class OauthController {

    private final OauthService oauthService;

    /**
     * 사용자로부터 SNS 로그인 요청을 Social Login Type 을 받아 처리
     *
     * @param socialLoginType (GOOGLE, NAVER)
     */
    @ApiOperation(
        httpMethod = "GET", value = "소셜 로그인", notes = "소셜 로그인 버튼 클릭")
    @GetMapping(value = "/{socialLoginType}")
    public void socialLoginType(
        @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) {
        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", socialLoginType);
        oauthService.request(socialLoginType);
    }

    /**
     * Social Login API Server 요청에 의한 callback 을 처리
     *
     * @param socialLoginType (GOOGLE, FACEBOOK, NAVER, KAKAO)
     * @param code            API Server 로부터 넘어노는 code
     * @return SNS Login 요청 결과로 받은 Json 형태의 String 문자열 (access_token, refresh_token 등)
     */
    @GetMapping(value = "/{socialLoginType}/callback")
    public ResponseEntity<?> callback(
        @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType,
        @RequestParam(name = "code") String code,
        @RequestParam(name= "state", required = false) String state) {

        String jwt = oauthService.requestAccessToken(socialLoginType, code, state);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(new TokenInRes(jwt)).build(),
            httpHeaders,
            HttpStatus.OK);
    }

}
