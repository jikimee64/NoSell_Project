package com.soap.moon.domains.member.controller;

import com.soap.moon.domains.member.domain.Account;
import com.soap.moon.domains.member.domain.Member;
import com.soap.moon.domains.member.dto.MemberDto;
import com.soap.moon.domains.member.dto.MemberDto.selectOneRes;
import com.soap.moon.domains.member.exception.MemberNotFoundException;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"1. Member"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class MemebrController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

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

    @ApiOperation(
        httpMethod = "GET", value = "회원 단건 조회", notes = "회원에 대한 정보를 조회한다.(단건)")
    @GetMapping(value = "/members/{id}")
    public ResponseEntity<?> getMember(
        @ApiParam(value = "회원단건 조회 폼입력필드 DTO", required = true)
        @PathVariable("id") final Long memberId) {

        Member member = memberRepository.findById(memberId)
            .orElseThrow(MemberNotFoundException::new);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    new selectOneRes(member.getId(), member.getAccount().getUserId(),member.getName()))
                .build()
            , HttpStatus.OK);
    }

//    @GetMapping("/user")
//    //@PreAuthorize("hasAnyRole('USER','ADMIN')")
//    public ResponseEntity<Member> getMyUserInfo() {
//        return ResponseEntity.ok(memberService.getMyUserWithAuthorities().get());
//    }
//
//    @GetMapping("/user/{username}")
//    //@PreAuthorize("hasAnyRole('ADMIN')")
//    public ResponseEntity<Member> getUserInfo(@PathVariable String username) {
//        return ResponseEntity.ok(memberService.getUserWithAuthorities(username).get());
//    }


}
