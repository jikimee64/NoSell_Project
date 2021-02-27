package com.soap.moon.domains.user.exception;

import com.soap.moon.global.error.ErrorCode;

public class JwtExpiredException extends RuntimeException{
    public JwtExpiredException() {
        super(ErrorCode.EXPIRED_JWT_TOKE.getMessage());
    }

    public JwtExpiredException(Exception ex) {
        super(ex);
    }


}