package com.soap.moon.domains.user.exception;

import com.soap.moon.global.error.ErrorCode;

public class JwtUnsupportedException extends RuntimeException{
    public JwtUnsupportedException() {
        super(ErrorCode.NOT_APPLY_JWT.getMessage());
    }

    public JwtUnsupportedException(Exception ex) {
        super(ex);
    }


}