package com.soap.moon.domains.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @ApiModel("로그인 요청값 POST")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class LoginReq {

        @ApiModelProperty(value = "아이디", notes = "userId", example = "test@test.com", required = true)
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Email
        public String userId;

        @ApiModelProperty(value = "패스워드", notes = "password", example = "password", required = true)
        @NotBlank(message = "패스워드는 필수 입력 값입니다.")
        @Size(min = 3, max = 50)
        public String password;
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

}
