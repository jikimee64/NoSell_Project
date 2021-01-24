package com.soap.moon.domains.member.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Member
    EMAIL_DUPLICATION(400, "M001", "Id is Duplication"),
    MEMBER_IN_ACTIVE(400, "M002", "회원이 정지된 상태입니다."),
    MEMBER_NOT_FOUND(400, "M003", "회원을 찾을 수 없습니다."),
    LOGIN_FAILED(401, "AUTH_001", "로그인에 실패하였습니다."),


    // Common
    BAD_REQUEST(400, "ERROR_001", "입력값이 잘못되었습니다."),
    MEMBER_DUPLICATION(400, "ERROR_001", "중복된 회원입니다.");


    private int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }


}
