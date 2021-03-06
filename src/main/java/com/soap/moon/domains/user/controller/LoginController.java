package com.soap.moon.domains.user.controller;

import com.soap.moon.domains.user.domain.RedisToken;
import com.soap.moon.domains.user.domain.Token;
import com.soap.moon.domains.user.dto.LoginDto;
import com.soap.moon.domains.user.dto.LoginDto.LoginRes;
import com.soap.moon.domains.user.dto.LoginDto.RefreshRes;
import com.soap.moon.domains.user.service.LoginService;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.global.config.aop.PerformanceTimeRecord;
import com.soap.moon.global.jwt.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = {"2. Login"}, value = "회원 로그인")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {

    private final LoginService loginService;
    private final RedisTemplate<String, Object> redisTemplate;

    @PerformanceTimeRecord
    @ApiOperation(
        httpMethod = "POST", value = "로그인", notes = "로그인을 한다.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "로그인 성공", response = LoginRes.class)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(
        @ApiParam(value = "로그인 폼입력값", required = true)
        @Valid @RequestBody LoginDto.LoginReq loginDto,
        HttpServletResponse response) {

        Map<String, Object> map = loginService.login(loginDto);

        RedisToken retok = new RedisToken();
        retok.setUsername(loginDto.getEmail());
        retok.setRefreshToken(String.valueOf(map.get(Token.REFRESH_TOKEN.getName())));

        //generate Token and save in redis
        ValueOperations<String, Object> vop = redisTemplate.opsForValue();
        vop.set(loginDto.getEmail(), retok);

        Cookie cookie = new Cookie("refreshToken", (String) map.get(Token.REFRESH_TOKEN.getName()));
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        //CommonResponse 빌더 패턴으로 생성시 알수없는 에러 발생
        return new ResponseEntity<>(
            new CommonResponse("200", "ok",
                LoginRes.builder()
                    .accessToken(String.valueOf(map.get(Token.ACCESS_TOKEN.getName())))
                    .nickName(String.valueOf(map.get("nickName")))
                    .profileImage(String.valueOf(map.get("profileImage")))
                    .build()),
            HttpStatus.OK);
    }

    @PerformanceTimeRecord
    @ApiOperation(
        httpMethod = "POST", value = "로그아웃", notes = "로그아웃을 한다. 저장소의 refresh Token이 사라지고 ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "로그아웃 성공", response = LoginRes.class)
    })
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
        @ApiParam(value = "로그아웃시 전송할 accessToken", required = true)
        @RequestBody LoginDto.LogoutReq logoutReq) {

        String username = loginService.logout(logoutReq);
        String accessToken = logoutReq.getAccessToken();

        try {
            if (redisTemplate.opsForValue().get(username) != null) {
                //delete refresh token
                redisTemplate.delete(username);
            }
        } catch (IllegalArgumentException e) {
            log.warn("user does not exist");
        }

        /**
         * cache logout token for 10 minutes!
         * Access token Blacklist에 30분동안(access token 만료 시간) 올라간다. 로그아웃한 access Token으로는 30분동안 사용 불가
         */
        //key, value 저장
        RedisToken redisToken = new RedisToken();
        redisToken.setAccessToken(accessToken);
        redisTemplate.opsForValue().set(accessToken, redisToken);
        redisTemplate.expire(accessToken, 30 * 6 * 1000, TimeUnit.MILLISECONDS);

        Map<String, Boolean> map = new HashMap();
        map.put("logoutCheck", true);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(map).build(),
            HttpStatus.OK);
    }

    @PerformanceTimeRecord
    @ApiOperation(
        httpMethod = "POST", value = "refresh 토큰 요청", notes = "refreshToken을 요청해 새로운 accessToken을 발급받는다")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "new AccessToken 요청 성공", response = RefreshRes.class)
    })
    @PostMapping("/refresh")
    public ResponseEntity<?> requestForNewAccessToken(
        @ApiParam(value = "토큰요청시 전송값", required = true)
        @RequestBody LoginDto.RefreshReq dto) {

        String newAccessToken = loginService.provideNewAccessToken(dto);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(RefreshRes.builder().accessToken(newAccessToken).build()).build(),
            HttpStatus.OK);
    }


}
