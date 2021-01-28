package com.soap.moon.domains.member.controller;

import com.soap.moon.domains.member.domain.User;
import com.soap.moon.domains.member.dto.UserDto.selectOneRes;
import com.soap.moon.domains.member.exception.MemberNotFoundException;
import com.soap.moon.domains.member.repository.UserRepository;
import com.soap.moon.domains.member.service.UserService;
import com.soap.moon.global.common.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"1. Member"}, value = "회원 CRUD")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/members")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @ApiOperation(
        httpMethod = "GET", value = "회원 단건 조회", notes = "회원에 대한 정보를 조회한다.(단건)")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getMember(
        @ApiParam(value = "회원단건 조회 폼입력필드 DTO", required = true)
        @PathVariable("id") final Long memberId) {

        User user = userRepository.findById(memberId)
            .orElseThrow(MemberNotFoundException::new);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    new selectOneRes(user.getId(), user.getAccount().getEmail(), user.getName()))
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
