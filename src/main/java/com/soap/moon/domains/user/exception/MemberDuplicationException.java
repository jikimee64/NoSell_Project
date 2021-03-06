package com.soap.moon.domains.user.exception;

import com.soap.moon.global.error.ErrorCode;

public class MemberDuplicationException extends RuntimeException {

    public MemberDuplicationException(String providerType) {
        super(
            providerType + ErrorCode.EMAIL_DUPLICATION.getMessage()
        );
    }

    public MemberDuplicationException(Exception ex) {
        super(ex);
    }
}
