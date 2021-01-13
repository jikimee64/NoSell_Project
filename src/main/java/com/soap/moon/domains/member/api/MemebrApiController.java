package com.soap.moon.domains.member.api;

import com.soap.moon.domains.member.dto.MemberDto;
import com.soap.moon.domains.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. Member"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
public class MemebrApiController {

    private MemberService memberService;

    @ApiOperation(value = "회원 가입", notes = "회원 가입을 한다.")
    @PostMapping(value="/member")
    public MemberDto.SignInRes signInMember(
        @ApiParam(value="회원가입 폼입력필드 DTO", required = true)
        @RequestBody @Valid final MemberDto.SignInReq dto,
        Errors errors){

        if(errors.hasErrors()){

        }

        return new MemberDto.SignInRes();
    }

}
