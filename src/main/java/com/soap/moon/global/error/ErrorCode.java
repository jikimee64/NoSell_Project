package com.soap.moon.global.error;

import lombok.Getter;

/**
 * 400 : Bad Request 401 : Unauthorized 403 : Forbidden 404 : Not Found 500 : Internal Server Error
 */
@Getter
public enum ErrorCode {

    // Member
    EMAIL_DUPLICATION("자체 계정을 통해 가입한 아이디입니다."),
    MEMBER_NOT_ACTIVE("회원이 정지된 상태입니다."),
    MEMBER_NOT_FOUND("회원을 찾을 수 없습니다."),
    MEMBER_LOGOUT("로그아웃된 상태입니다."),
    //PASSWORD_NOT_MATCH(400, "M004", "비밀번호를 다시 입력해주세요."),


    // Common
    BAD_REQUEST("입력값이 잘못되었습니다."),
    MEMBER_DUPLICATION("중복된 회원입니다."),
    RUNTIME_EXCEPTION("서버에서 에러가 발생했습니다."),

    // Auth
    AUTHENTICATION_FAILED("인증에 실패하였습니다."),
    LOGIN_FAILED("아이디 혹은 비밀번호가 틀립니다."),
    INVALID_JWT_TOKEN("유효하지 않은 토큰입니다."),
    EXPIRED_JWT_TOKEN("만료된 토큰입니다."),
    NOT_FORM_JWT("잘못된 JWT 서명입니다."),
    NOT_APPLY_JWT("지원되지 않는 JWT 토큰입니다."),
    ACCESS_DENIED("접근 권한이 없습니다."),

    // Product
    PRODUCT_NOT_FOUND("상품을 더이상 찾을 수 없습니다.");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

}
