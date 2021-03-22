package com.soap.moon.domains.user.dto;

import com.soap.moon.global.util.ValidationGroups;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDto {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class SignInReq {

        @ApiModelProperty(value = "아이디(이메일 형식)", example = "test@test.com", required = true, position = 1)
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Email(message = "이메일 형식으로 작성해주세요.")
        @Size(max = 30, message = "30자 이하로 입력해주세요.")
        private String email;

        //정규표현식 순서 추가
        @ApiModelProperty(value = "비밀번호", example = "password", required = true, position = 2)
        @NotBlank(message = "패스워드는 필수 입력 값입니다.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,15}",
            message = "비밀번호는 영문 소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 6자 ~ 15자의 비밀번호여야 합니다.")
        private String password;

        @ApiModelProperty(value = "휴대폰번호", example = "01099991111", required = true, position = 3)
        @NotBlank(message = "휴대폰번호는 필수 입력 값입니다.")
        @Size(max = 11, message = "휴대폰번호는 11자로 입력해주세요.")
        private String phoneNum;

        @ApiModelProperty(value = "닉네임", example = "홍길동", required = true, position = 4)
        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
        @Size(min = 2, message = "닉네임은 최소 2자 이상으로 입력해주세요.")
        private String nickName;
    }


    @ApiModel("회원가입시 이메일 중복 확인")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class EmailCheckReq {

        @ApiModelProperty(value = "아이디", notes = "userId", example = "test@test.com", required = true)
        @NotBlank(message = "아이디는 필수 입력 값입니다.")
        @Email(message = "아이디 형식에 맞지 않습니다.")
        @Size(max = 30, message = "아이디는 30자 이하로 입력해주세요.")
        public String email;
    }
    @ApiModel("gcp 파일 업로드")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class  UploadReqDto {
        private String bucketName;
        private String uploadFileName;
        private String localFileLocation;
    }


    @ApiModel("gcp 파일 다운")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class DownloadReqDto {
        private String bucketName;
        private String downloadFileName;
        private String localFileLocation;
    }


    @ApiModel("회원가입시 휴대폰 인증 확인")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class PhoneCheckReq {

        @ApiModelProperty(value = "휴대폰번호", notes = "phoneNum", example = "01099991111", required = true)
        @NotBlank(message = "휴대폰번호는 필수 입력 값입니다.")
        @Size(max = 12, message = "휴대폰번호는 11자로 입력해주세요.")
        public String phoneNum;
    }

    @ApiModel("회원수정시 닉네임 변경")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class updateNicknameReq {

        @ApiModelProperty(value = "닉네임", notes = "nickName", example = "홍길동", required = true)
        @NotBlank(message = "닉네임은 필수 입력 값입니다.")
        @Size(min = 2, message = "닉네임은 최소 2자 이상으로 입력해주세요.")
        public String nickName;
    }

    @ApiModel("회원수정시 비밀번호 변경")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class updatePasswordReq {

        @ApiModelProperty(value = "현재 비밀번호", example = "1q2w3e4r1!", required = true, position = 1)
        @NotBlank(message = "현재 패스워드는 필수 입력 값입니다.")
        public String nowPassword;

        @ApiModelProperty(value = "신규 비밀번호", example = "1q2w3e4r2@", required = true, position = 2)
        @NotBlank(message = "패스워드는 필수 입력 값입니다.", groups = ValidationGroups.NotEmptyGroup.class)
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\W)(?=\\S+$).{6,15}",
            message = "비밀번호는 영문 소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 6자 ~ 15자의 비밀번호여야 합니다."
            , groups = ValidationGroups.PatternCheckGroup.class)
        public String newPassword;
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

    @ApiModel("아이디 찾기시 받을 아이디값")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class MailDto {

        private String toEmail;
    }

}
