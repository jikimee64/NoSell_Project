package com.soap.moon.domains.member.exception;

public class MemberDuplicationException extends RuntimeException {
    public MemberDuplicationException() {
    }
    public MemberDuplicationException(String message) {
        super(message);
    }
    public MemberDuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    public MemberDuplicationException(Throwable cause) {
        super(cause);
    }
}
