package com.soap.moon.domains.user.exception;

import com.soap.moon.global.error.ErrorCode;

public class CustomJwtRuntimeException extends RuntimeException{
    public CustomJwtRuntimeException(){
        super(ErrorCode.INVALID_JWT_TOKEN.getMessage());
    }

    public CustomJwtRuntimeException(Exception ex){
        super(ex);
    }
}
