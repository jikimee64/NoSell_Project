package com.soap.moon.domains.member.dto;

import javax.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TokenDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class TokenInReq{

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class TokenInRes{
        public String token;

        public TokenInRes(String token) {
            this.token = token;
        }
    }
}
