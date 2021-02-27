package com.soap.moon.domains.user.controller;

import com.soap.moon.domains.user.domain.RedisToken;
import com.soap.moon.domains.user.domain.Token;
import com.soap.moon.domains.user.dto.LoginDto;
import com.soap.moon.domains.user.dto.LoginDto.LoginRes;
import com.soap.moon.domains.user.dto.LoginDto.refreshRes;
import com.soap.moon.domains.user.service.LoginService;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.global.jwt.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(
        httpMethod = "POST", value = "로그인", notes = "로그인을 한다.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "로그인 성공", response = LoginRes.class)
    })
    @PostMapping("/login")
    public ResponseEntity<?> authorize(
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

        Cookie cookie = new Cookie("refreshToken", (String)map.get(Token.REFRESH_TOKEN.getName()));
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(new LoginDto.LoginRes(String.valueOf(map.get(Token.ACCESS_TOKEN.getName())),
                    Long.valueOf(String.valueOf(map.get("id"))))).build(),
            HttpStatus.OK);
    }

    @ApiOperation(
        httpMethod = "POST", value = "로그아웃", notes = "로그아웃을 한다. 저장소의 refresh Token이 사라지고 ")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "로그아웃 성공", response = LoginRes.class)
    })
    @PostMapping("/logout")
    public ResponseEntity<?> logout(
        @ApiParam(value = "로그아웃시 전송할 accessToken", required = true)
        @RequestBody LoginDto.logoutReq logoutReq) {

        String username = null;
        String accessToken = logoutReq.getAccessToken();
        try {
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            username = authentication.getName();
            log.info("username : " + username);
        } catch (IllegalArgumentException e) {} catch (ExpiredJwtException e) { //expire됐을 때
            username = e.getClaims().getSubject();
            log.info("username from expired access token: " + username);
        }

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
        log.info(" logout ing : " + accessToken);
        //key, value 저장
        RedisToken redisToken = new RedisToken();
        redisToken.setAccessToken(accessToken);
        redisTemplate.opsForValue().set(accessToken, redisToken);
        redisTemplate.expire(accessToken, 30*6*1000, TimeUnit.MILLISECONDS);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data("").build(),
            HttpStatus.OK);
    }

    @ApiOperation(
        httpMethod = "POST", value = "refresh 토큰 요청", notes = "refreshToken을 요청해 새로운 accessToken을 발급받는다")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "new AccessToken 요청 성공", response = refreshRes.class)
    })
    @PostMapping("/refresh")
    public ResponseEntity<?>  requestForNewAccessToken(
        @ApiParam(value = "토큰요청시 전송값", required = true)
        @RequestBody LoginDto.refreshReq dto) {

        String newAccessToken = loginService.provideNewAccessToken(dto);

//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER,
//            "Bearer " + (map.get(Token.ACCESS_TOKEN.getName())));

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(refreshRes.builder().accessToken(newAccessToken).build()).build(),
//            httpHeaders,
            HttpStatus.OK);
    }



}
