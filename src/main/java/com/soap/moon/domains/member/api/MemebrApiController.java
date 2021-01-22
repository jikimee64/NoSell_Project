package com.soap.moon.domains.member.api;

import com.soap.moon.domains.member.domain.Member;
import com.soap.moon.domains.member.dto.MemberDto;
import com.soap.moon.domains.member.exception.ErrorCode;
import com.soap.moon.domains.member.service.MemberService;
import com.soap.moon.global.common.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.net.URI;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"1. Member"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class MemebrApiController {

    private final MemberService memberService;

    @ApiOperation(
        httpMethod = "POST", value = "회원 가입", notes = "회원 가입을 한다.")
    @PostMapping(value = "/members")
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
