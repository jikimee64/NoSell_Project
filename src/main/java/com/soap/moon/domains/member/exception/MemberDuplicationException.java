package com.soap.moon.domains.member.exception;

public class MemberDuplicationException extends RuntimeException {
    public MemberDuplicationException() {
        super(ErrorCode.EMAIL_DUPLICATION.getMessage());
    }

    public MemberDuplicationException(Exception ex) {
        super(ex);
    }
}
