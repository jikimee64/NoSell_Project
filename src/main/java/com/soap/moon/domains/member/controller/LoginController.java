package com.soap.moon.domains.member.controller;

import com.soap.moon.domains.member.dto.UserDto;
import com.soap.moon.domains.member.dto.TokenDto.TokenInRes;
import com.soap.moon.domains.member.service.LoginService;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.infra.jwt.JwtFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = {"3. Login"}, value = "회원 로그인")
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {

    private final LoginService loginService;

    @ApiOperation(
        httpMethod = "POST", value = "로그인", notes = "로그인을 한다.")
    @PostMapping("/login")
    public ResponseEntity<?> authorize(
        @ApiParam(value = "로그인 폼입력값", required = true)
        @Valid @RequestBody UserDto.LoginReq loginDto) {

        String jwt = loginService.login(loginDto);

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
