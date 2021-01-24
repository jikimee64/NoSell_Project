package com.soap.moon.domains.member.controller;

import com.soap.moon.domains.member.dto.MemberDto;
import com.soap.moon.domains.member.repository.MemberRepository;
import com.soap.moon.domains.member.service.MemberService;
import com.soap.moon.global.common.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"1. Singup"}, value = "회원가입")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class SignupController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

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
