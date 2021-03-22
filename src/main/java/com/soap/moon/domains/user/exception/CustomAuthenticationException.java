package com.soap.moon.domains.user.exception;

import com.soap.moon.global.error.ErrorCode;

public class CustomAuthenticationException extends RuntimeException {

    public CustomAuthenticationException() {
        super(ErrorCode.AUTHENTICATION_FAILED.getMessage());
    }

    public CustomAuthenticationException(Exception ex) {
        super(ex);
    }
}
