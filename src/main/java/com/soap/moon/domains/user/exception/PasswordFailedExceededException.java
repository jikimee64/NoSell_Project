package com.soap.moon.domains.user.exception;

public class PasswordFailedExceededException extends RuntimeException {

    public PasswordFailedExceededException() {
    }

    public PasswordFailedExceededException(String message) {
        super(message);
    }

    public PasswordFailedExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordFailedExceededException(Throwable cause) {
        super(cause);
    }
}
