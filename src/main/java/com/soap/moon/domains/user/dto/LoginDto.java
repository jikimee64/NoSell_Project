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

public class LoginDto {

    @ApiModel("로그인 요청값 POST")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class LoginReq {

        @ApiModelProperty(value = "아이디", notes = "userId", example = "nosell@nosell.com", required = true)
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Email
        public String email;

        @ApiModelProperty(value = "패스워드", notes = "password", example = "nosell", required = true)
        @NotBlank(message = "패스워드는 필수 입력 값입니다.")
        @Size(min = 3, max = 50)
        public String password;
    }

    @ApiModel("로그인 후 id값, 토큰 값(accessToken, refreshToken 반환")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class LoginRes{
        private String accessToken;
        private String nickName;
        private String profileImage;
    }

    @ApiModel("로그아웃 요청시 요청값")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class LogoutReq{
        @ApiModelProperty(value = "accessToken", notes = "accessToken", example = "accessToken", required = true)
        private String accessToken;
    }

    @ApiModel("토큰 refresh 요청시 요청값")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class RefreshReq{
        @ApiModelProperty(value = "accessToken", notes = "accessToken", example = "accessToken", required = true)
        private String accessToken;
        @ApiModelProperty(value = "refreshToken", notes = "refreshToken", example = "refreshToken", required = true)
        private String refreshToken;
    }

    @ApiModel("토큰 refresh 요청시 반환값")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class RefreshRes{
        private String accessToken;
    }

}
