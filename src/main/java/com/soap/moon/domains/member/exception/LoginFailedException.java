package com.soap.moon.domains.member.exception;

public class LoginFailedException extends RuntimeException{
    public LoginFailedException() {
        super(ErrorCode.LOGIN_FAILED.getMessage());
    }

    public LoginFailedException(Exception ex) {
        super(ex);
    }


}
