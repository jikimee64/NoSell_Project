package com.soap.moon.domains.member.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Member
    EMAIL_DUPLICATION(400, "M001", "Id is Duplication"),
    LOGIN_FAILED(401, "AUTH_001", "로그인에 실패하였습니다."),

    // Common
    BAD_REQUEST(400, "ERROR_001", "입력값이 잘못되었습니다."),
    MEMBER_DUPLICATEREQUEST(400, "ERROR_001", "입력값이 잘못되었습니다.");


    private int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }


}
