package com.soap.moon.domains.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

public class UserDto {

    @ApiModel("회원가입 POST RequestParam 객체 도메인")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class SignInReq {

        @ApiModelProperty(value = "아이디", notes = "userId", example = "admin@j2kb.com", required = true)
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Email(message = "아이디 형식에 맞지 않습니다.")
        @Size(max = 30, message = "아이디는 30자 이하로 입력해주세요.")
        public String email;

        @ApiModelProperty(value = "비밀번호", notes = "password", example = "admin", required = true)
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

        @Builder
        public SignInReq(String email, String password, String phoneNum, String nickName) {
            this.email = email;
            this.password = password;
            this.phoneNum = phoneNum;
            this.nickName = nickName;
        }
    }

    @ApiModel("로그인 POST RequestParam 객체 도메인")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class LoginReq {

        @ApiModelProperty(value = "아이디", notes = "userId", example = "dncjf64", required = true)
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Size(min = 3, max = 50)
        public String userId;

        @ApiModelProperty(value = "패스워드", notes = "password", example = "password", required = true)
        @NotBlank(message = "패스워드는 필수 입력 값입니다.")
        @Size(min = 3, max = 50)
        public String password;
    }

    @ApiModel("회원 단건 조회 GET")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class selectOneRes {

        @ApiModelProperty(value = "고유값", notes = "id")
        public Long id;

        @ApiModelProperty(value = "아이디", notes = "email")
        public String email;

        @ApiModelProperty(value = "이름", notes = "name")
        public String name;


    }

}
