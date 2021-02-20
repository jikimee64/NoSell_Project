package com.soap.moon.domains.user.controller;

import com.soap.moon.domains.user.domain.Token;
import com.soap.moon.domains.user.dto.LoginDto;
import com.soap.moon.domains.user.dto.UserDto;
import com.soap.moon.domains.user.service.LoginService;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.global.jwt.JwtFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"2. Login"}, value = "회원 로그인")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {

    private final LoginService loginService;

    @ApiOperation(
        httpMethod = "POST", value = "로그인", notes = "로그인을 한다.")
    @PostMapping("/login")
    public ResponseEntity<?> authorize(
        @ApiParam(value = "로그인 폼입력값", required = true)
        @Valid @RequestBody LoginDto.LoginReq loginDto) {

        Map<String, Object> map = loginService.login(loginDto);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER,
            "Bearer " + (map.get(Token.ACCESS_TOKEN.getName())));

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(new LoginDto.LoginRes(String.valueOf(map.get(Token.ACCESS_TOKEN.getName())),
                    Long.valueOf(String.valueOf(map.get("id"))))).build(),
            httpHeaders,
            HttpStatus.OK);
    }

}
