package com.soap.moon.domains.user.dto;

import io.swagger.annotations.ApiModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class AuthDto {

//    @ApiModel("구글/네이버 토큰 GET")
//    @Data
//    @Builder
//    @AllArgsConstructor
//    @NoArgsConstructor(access = AccessLevel.PUBLIC)
//    public static class TokenRes{
//        private String access_token;
//        private int expires_in;
//        private String refresh_token;
//        //private String scope;
//        //private String token_type;
//        //private String id_token;
//    }

    @ApiModel("Client에서 토큰 받기")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class AccessTokenReq {

        private String accessToken;
    }


    @ApiModel("구글 유저정보 GET")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class GoogleProfileRes {

        private String id;
        private String email;
        //private String picture;
    }


    @ApiModel("네이버 유저정보 GET")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class NaverProfileRes {

        private Response response;
        private String resultcode;
        private String message;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor(access = AccessLevel.PUBLIC)
        public static class Response {

            private String id;
            private String name;
            private String email;
            private String gender;
            private String age;
            private String birthday;
            private String profile_image;
        }

    }


}
