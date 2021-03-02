package com.soap.moon.domains.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDto {

    @ApiModel("회원가입 요청값 POST")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class SignInReq {

        @ApiModelProperty(value = "아이디", notes = "userId", example = "test@test.com", required = true)
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Email(message = "아이디 형식에 맞지 않습니다.")
        @Size(max = 30, message = "아이디는 30자 이하로 입력해주세요.")
        public String email;

        @ApiModelProperty(value = "비밀번호", notes = "password", example = "password", required = true)
        @NotBlank(message = "패스워드는 필수 입력 값입니다.")
//        @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
//            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
        @Size(max = 15, message = "비밀번호는 15자 이하로 입력해주세요.")
        public String password;

        @ApiModelProperty(value = "휴대폰번호", notes = "phoneNum", example = "01099991111", required = true)
        @NotBlank(message = "휴대폰번호는 필수 입력 값입니다.")
        @Size(max = 12, message = "휴대폰번호는 11자로 입력해주세요.")
        public String phoneNum;

        @ApiModelProperty(value = "닉네임", notes = "nickName", example = "홍길동", required = true)
        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
        @Size(min = 2, message = "닉네임은 최소 2자 이상으로 입력해주세요.")
        public String nickName;
    }


    @ApiModel("회원 단건 조회 GET")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class SelectOneRes {

        public Long id;
        public String email;
        public String name;
    }

    @ApiModel("회원 토큰값 이용한 권한 검사")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class CheckUserAuthRes {
        private String nickName;
        private String profileImage;
    }

}
