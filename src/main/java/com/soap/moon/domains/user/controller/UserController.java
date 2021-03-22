package com.soap.moon.domains.user.controller;

import static com.soap.moon.global.util.ThrowUtil.hasErrorsThrow;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.dto.UserDto;
import com.soap.moon.domains.user.dto.UserDto.CheckUserAuthRes;
import com.soap.moon.domains.user.dto.UserDto.DownloadReqDto;
import com.soap.moon.domains.user.dto.UserDto.SelectOneRes;
import com.soap.moon.domains.user.dto.UserDto.UploadReqDto;
import com.soap.moon.domains.user.exception.MemberNotFoundException;
import com.soap.moon.domains.user.repository.UserRepository;
import com.soap.moon.domains.user.service.UserService;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.global.common.ExceptionResponse;
import com.soap.moon.global.common.ResponseTemplate;
import com.soap.moon.global.config.aop.PerformanceTimeRecord;
import com.soap.moon.global.util.ValidationSequence;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;


@Slf4j
@Api(tags = {"1. User"}, value = "회원 CRUD")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final UserService userService;
    private final UserRepository userRepository;

    /**
     * 나중에~~~
     *
     * @param request
     * @return
     */
    @ApiOperation(
        httpMethod = "GET", value = "유저 권한검사", notes = "header에 담긴 토큰값을 꺼내서 권한검사한뒤 유저정보를 반환")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "토큰 유효성 검사 조회 성공", response = CheckUserAuthRes.class)
    })
    @GetMapping("/auth")
    public ResponseEntity<?> checkUserAuth(
        @ApiParam(value = "헤더에 담긴 토큰값", required = true)
            HttpServletRequest request) {

        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.checkUserAuth(bearerToken.substring(7)));

//        return new ResponseEntity<>(
//            CommonResponse.builder()
//                .code("200")
//                .message("ok")
//                .data(userService.checkUserAuth(bearerToken.substring(7)))
//                .build()
//            , HttpStatus.OK);
    }

    /**
     * 1순위
     */
    @ApiOperation(value = "회원 가입", notes = "회원가입을 합니다.")
    @PostMapping
    public ResponseEntity<?> saveUser(
        @ApiParam(value = "회원가입시 전송해야할 정보", required = true)
        @RequestBody @Valid final UserDto.SignInReq dto) {

        Map<String, Long> map = new HashMap<>();
        map.put("id", userService.save(dto).getId());

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(map)
                .build()
            , HttpStatus.OK);
    }

    @ApiOperation(
        httpMethod = "POST", value = "아이디 중복 체크", notes = "회원가입 시 아이디 중복체크를 한다.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "이메일 중복 체크 성공", response = Map.class)
    })
    @PostMapping("/emailCheck")
    public ResponseEntity<?> checkEmailOfDuplication(
        @ApiParam(value = "이메일", required = true)
        @RequestBody @Valid final UserDto.EmailCheckReq dto) {

        Map<String, Boolean> map = new HashMap<>();
        boolean flag = userService.validateDuplicateMember(dto.getEmail());
        map.put("confirm", flag);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(map)
                .build()
            , HttpStatus.OK);
    }


    @ApiOperation(
        httpMethod = "POST", value = "sms 인증 체크", notes = "회원가입 시 휴대폰 인증을 한다.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "휴대폰 인증 성공", response = Map.class)
    })
    @PostMapping("/sendSMS")
    public ResponseEntity<?> checkPhoneSms(
        @ApiParam(value = "휴대폰번호", required = true)
        @RequestBody @Valid final UserDto.PhoneCheckReq dto) {

        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }

        Map<String, String> map = new HashMap<>();
        if (userService.certifiedPhoneNumber(dto.getPhoneNum(), numStr)) {
            map.put("AuthNumber", numStr);
        }

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(map)
                .build()
            , HttpStatus.OK);
    }

    //임시 로직
    @ApiOperation(
        httpMethod = "GET", value = "회원 단건 조회", notes = "회원에 대한 정보를 조회한다.(단건)")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "회원 단건 조회 성공", response = SelectOneRes.class)
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getMember(
        @ApiParam(value = "회원단건 조회 DTO", required = true)
        @PathVariable("id") final Long memberId) {

        User user = userRepository.findById(memberId)
            .orElseThrow(MemberNotFoundException::new);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    new SelectOneRes(user.getId(), user.getAccount().getEmail(),
                        user.getNickName()))
                .build()
            , HttpStatus.OK);
    }

    //사진 등록
    @ApiOperation( value = "썸네일 변경", notes = "회원수정에서 썸네일을 변경한다.")
    @ApiResponses(value = {
        //@ApiResponse(code = , message = "", response = Map.class)
    })
    @PostMapping(value = "/{id}/upload")
    public ResponseEntity<?> uploadToStorage(
        @PathVariable("id") final Long memberId,
        @ApiParam(value = "회원수정 닉네임 DTO", required = true)
        @RequestBody UploadReqDto uploadReqDto
    ){

        BlobInfo blobInfo = userService.uploadFileToGCS(uploadReqDto);
        log.info("blobInfo : " + blobInfo);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseTemplate.create(
                "Gdc"
            ));
    }

    //사진 다운(되나 테스트용)
    //되는데 .. 이방법말고 그냥 db에 다운경로가 저장된것을 가져오는 방식은 어떨까?
    @ApiOperation(value = "썸네일 변경", notes = "회원수정에서 썸네일을 변경한다.")
    @ApiResponses(value = {
        //@ApiResponse(code = , message = "", response = Map.class)
    })
    @PostMapping(value = "/{id}/download")
    public ResponseEntity<?> downloadFromStorage(
        @PathVariable("id") final Long memberId,
        @ApiParam(value = "회원수정 닉네임 DTO", required = true)
        @RequestBody DownloadReqDto downloadReqDto
    ){

        Blob fileFromGCS = userService.downloadFileFromGCS(downloadReqDto);

        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseTemplate.create(
                fileFromGCS.toString()
            ));
    }


    //휴대폰 번호 변경


    //닉네임 변경
    @ApiOperation(
        httpMethod = "PATCH", value = "닉네임 변경", notes = "회원수정에서 닉네임을 변경한다.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "회원 닉네임 변경 성공", response = Map.class)
    })
    @PatchMapping(value = "/{id}/nickname")
    public ResponseEntity<?> updateNickname(
        @PathVariable("id") final Long memberId,
        @ApiParam(value = "회원수정 닉네임 DTO", required = true)
        @RequestBody @Valid final UserDto.updateNicknameReq dto
    ) {
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    userService.updateMemberOfNickname(memberId, dto.getNickName()))
                .build()
            , HttpStatus.OK);
    }

    //비밀번호 변경
    @ApiOperation(value = "비밀번호 변경", notes = "회원수정에서 비밀번호를 변경한다.")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "비밀번호 요청값 에러", response = ExceptionResponse.class)
    })
    @PatchMapping(value = "/{id}/password")
    public ResponseEntity<?> updatePassword(
        @PathVariable("id") final Long memberId,
        @ApiParam(value = "회원수정 비밀번호 DTO", required = true)
        @RequestBody @Validated(ValidationSequence.class) final UserDto.updatePasswordReq dto,
        @ApiIgnore Errors errors
    ) {
        hasErrorsThrow(errors);
        return ResponseEntity.status(HttpStatus.OK)
            .body(ResponseTemplate.create(
                userService.updateMemberOfPassword(memberId, dto.getNowPassword(), dto.getNewPassword())
            ));
    }

    //회원탈퇴
    @ApiOperation(value = "회원탈퇴", notes = "회원수정에서 회원탈퇴를 한다.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "회원 탈퇴 성공", response = Map.class)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(
        @PathVariable("id") final Long memberId
    ) {
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    userService.deleteMember(memberId))
                .build()
            , HttpStatus.OK);
    }


    //비밀번호 찾기(이메일 인증)
    @ApiOperation(value = "비밀번호 찾기", notes = "비밀번호 찾기")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "아이디 찾기 성공", response = Map.class)
    })
    @PerformanceTimeRecord
    @PostMapping("/mail")
    public ResponseEntity<?> findId(
        @RequestBody @Valid final UserDto.MailDto dto
    ) {

        Random rand = new Random();
        String numStr = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }

        Map<String, String> map = new HashMap<>();
        userService.findIdInEmail(numStr, dto.getToEmail());
        map.put("AuthNumber", numStr);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(map)
                .build()
            , HttpStatus.OK);
    }

}