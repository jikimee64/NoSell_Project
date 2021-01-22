package com.soap.moon.domains.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

public class MemberDto {

    @ApiModel("회원가입 POST RequestParam 객체 도메인") // 모델명
    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class SignInReq {

        @ApiModelProperty(value = "아이디", notes = "userId", example = "dncjf64", required = true)
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Email(message = "아이디 형식에 맞지 않습니다.")
        @Size(max = 30, message = "아이디는 30자 이하로 입력해주세요.")
        public String userId;

        @ApiModelProperty(value = "비밀번호", notes = "password", example = "password", required = true)
        @NotBlank(message = "패스워드는 필수 입력 값입니다.")
        @Size(max = 16, message = "비밀번호는 15자 이하로 입력해주세요.")
        public String password;

        @ApiModelProperty(value = "이름", notes = "name", example = "홍길동", required = true)
        @NotBlank(message = "이름은 필수 입력 값입니다.")
        @Size(max = 20, message = "이름은는 20자 이하로 입력해주세요.")
        public String name;

        @Builder
        public SignInReq(String userId, String password, String name) {
            this.userId = userId;
            this.password = password;
            this.name = name;
        }
    }

    @ApiModel("로그인 POST RequestParam 객체 도메인") // 모델명
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

}
