package com.soap.moon.domains.user.exception;

import com.soap.moon.global.error.ErrorCode;

public class JwtMalFormedException extends RuntimeException{
    public JwtMalFormedException() {
        super(ErrorCode.NOT_FORM_JWT.getMessage());
    }

    public JwtMalFormedException(Exception ex) {
        super(ex);
    }


}
