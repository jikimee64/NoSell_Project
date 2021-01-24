package com.soap.moon.domains.member.controller;

import com.soap.moon.domains.member.dto.MemberDto;
import com.soap.moon.domains.member.dto.TokenDto;
import com.soap.moon.domains.member.dto.TokenDto.TokenInRes;
import com.soap.moon.domains.member.repository.MemberRepository;
import com.soap.moon.domains.member.service.MemberService;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.infra.jwt.JwtFilter;
import com.soap.moon.infra.jwt.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

//    public AuthController(JwtTokenProvider jwtTokenProvider,
//        AuthenticationManagerBuilder authenticationManagerBuilder,
//        ) {
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.authenticationManagerBuilder = authenticationManagerBuilder;
//    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto.TokenInRes> authorize(@Valid @RequestBody MemberDto.LoginReq loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword());

        //authenticate() 실행시 loadUserByUsername 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenInRes(jwt), httpHeaders, HttpStatus.OK);
    }


    @ApiOperation(
        httpMethod = "POST", value = "회원 가입", notes = "회원 가입을 한다.")
    @PostMapping(value = "/signup")
    public ResponseEntity<?> signInMember(
        @ApiParam(value = "회원가입 폼입력필드 DTO", required = true)
        @RequestBody @Valid final MemberDto.SignInReq dto) {

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(memberService.save(dto).getId())
                .build()
            , HttpStatus.OK);
    }
}