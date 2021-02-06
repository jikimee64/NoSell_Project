package com.soap.moon.domains.user.exception;

import com.soap.moon.global.error.ErrorCode;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException() {
        super(ErrorCode.LOGIN_FAILED.getMessage());
    }

    public LoginFailedException(Exception ex) {
        super(ex);
    }


}
