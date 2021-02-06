package com.soap.moon.domains.user.exception;

import com.soap.moon.global.error.ErrorCode;

public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND.getMessage());
    }

    public MemberNotFoundException(Exception ex) {
        super(ex);
    }
}
