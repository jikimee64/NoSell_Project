package com.soap.moon.domains.member.exception;

public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException() {
        super(ErrorCode.MEMBER_NOT_FOUND.getMessage());
    }

    public MemberNotFoundException(Exception ex) {
        super(ex);
    }
}
