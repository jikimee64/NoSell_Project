package com.soap.moon.domains.member.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    OK(200, "OK_001", "로그인에 실패하였습니다."),

    LOGIN_FAILED(401, "AUTH_001", "로그인에 실패하였습니다.");

    private int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }


}
