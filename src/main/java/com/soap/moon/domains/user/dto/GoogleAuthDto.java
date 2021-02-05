package com.soap.moon.domains.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class GoogleAuthDto {

    @ApiModel("구글 토큰 GET")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class TokenRes{
        private String access_token;
        private int expires_in;
        private String refresh_token;
        //private String scope;
        //private String token_type;
        //private String id_token;
    }

    @ApiModel("구글 유저정보 GET")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class GoogleProfileRes{
        private String email;
        private String picture;
    }


}
