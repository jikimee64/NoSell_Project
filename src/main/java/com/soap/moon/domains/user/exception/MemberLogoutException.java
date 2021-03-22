package com.soap.moon.domains.user.exception;

import com.soap.moon.global.error.ErrorCode;

public class MemberLogoutException extends RuntimeException {

    public MemberLogoutException() {
        super(ErrorCode.MEMBER_LOGOUT.getMessage());
    }

    public MemberLogoutException(Exception ex) {
        super(ex);
    }
}

