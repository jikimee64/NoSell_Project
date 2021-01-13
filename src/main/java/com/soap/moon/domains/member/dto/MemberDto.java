package com.soap.moon.domains.member.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;

public class MemberDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class SignInReq{

        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        public String userId;

        @NotBlank(message = "패스워드는 필수 입력 값입니다.")
        public String password;

        @NotBlank(message = "이름은 필수 입력 값입니다.")
        public String name;

        @Email(message = "이메일 형식에 맞지 않습니다.")
        @NotBlank(message = "이메일은 필수 입력 값입니다.")
        public String email;

        @Builder
        public SignInReq(String userId, String password, String name, String email){
            this.userId = userId;
            this.password = password;
            this.name = name;
            this.email = email;
        }

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class SignInRes{


    }

}
