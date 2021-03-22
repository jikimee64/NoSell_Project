package com.soap.moon.domains.user.controller;

import com.soap.moon.domains.user.domain.ProviderType;
import com.soap.moon.domains.user.domain.Token;
import com.soap.moon.domains.user.dto.AuthDto.AccessTokenReq;
import com.soap.moon.domains.user.dto.LoginDto;
import com.soap.moon.domains.user.dto.LoginDto.LoginRes;
import com.soap.moon.domains.user.service.OauthService;
import com.soap.moon.global.common.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"4. OAuth"}, value = "소셜 로그인")
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/oauth")
@Slf4j
public class OauthController {

    private final OauthService oauthService;

    /**
     * 사용자로부터 SNS 로그인 요청을 Social Login Type 을 받아 처리
     *
     * @param providerType (GOOGLE, NAVER)
     */
//    @ApiOperation(
//        httpMethod = "GET", value = "소셜 로그인", notes = "소셜 로그인 버튼 클릭, 내부에서 callback메소드로 이동")
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "소셜 로그인 성공", response = LoginRes.class)
//    })
//    @GetMapping(value = "/{socialLoginType}")
//    public void socialLoginType(
//        @PathVariable(name = "socialLoginType") ProviderType providerType) {
//        log.info(">> 사용자로부터 SNS 로그인 요청을 받음 :: {} Social Login", providerType);
//        oauthService.request(providerType);
//    }

    /**
     * Social Login API Server 요청에 의한 callback 을 처리
     *
     * @param providerType (GOOGLE, FACEBOOK, NAVER, KAKAO)
     * @param code         API Server 로부터 넘어노는 code
     * @return SNS Login 요청 결과로 받은 Json 형태의 String 문자열 (access_token, refresh_token 등)
     */
    @ApiOperation(
        httpMethod = "POST", value = "소셜 로그인", notes = "소셜로그인 시 타는 메소드")
    @PostMapping(value = "/{socialLoginType}/accessToken")
    public ResponseEntity<?> callback(
        @PathVariable(name = "socialLoginType") ProviderType providerType,
        @ApiParam(value = "accessToken", required = true)
        @RequestBody AccessTokenReq dto,
        HttpServletResponse response) {

        Map<String, Object> map = oauthService
            .requestAccessToken(providerType, dto);

        Cookie cookie = new Cookie("refreshToken", (String) map.get(Token.REFRESH_TOKEN.getName()));
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return new ResponseEntity<>(
            new CommonResponse("200", "ok",
                LoginRes.builder()
                    .accessToken(String.valueOf(map.get(Token.ACCESS_TOKEN.getName())))
                    .nickName(String.valueOf(map.get("nickName")))
                    .profileImage(String.valueOf(map.get("profileImage")))
                    .build()),
            HttpStatus.OK);
    }

}
