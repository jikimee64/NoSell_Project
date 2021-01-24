package com.soap.moon.domains.member.controller;

import com.soap.moon.domains.member.dto.MemberDto;
import com.soap.moon.domains.member.dto.TokenDto;
import com.soap.moon.domains.member.dto.TokenDto.TokenInRes;
import com.soap.moon.domains.member.exception.LoginFailedException;
import com.soap.moon.domains.member.repository.MemberRepository;
import com.soap.moon.domains.member.service.LoginService;
import com.soap.moon.domains.member.service.MemberService;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.infra.jwt.JwtFilter;
import com.soap.moon.infra.jwt.JwtTokenProvider;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> authorize(@Valid @RequestBody MemberDto.LoginReq loginDto) {

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
