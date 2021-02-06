package com.soap.moon.domains.user.exception;

import com.soap.moon.domains.user.domain.UserStatus;

public class MemberStatusInActiveException extends RuntimeException {

    public MemberStatusInActiveException() {
        super(UserStatus.INACTIVE.getTitle());
    }

    public MemberStatusInActiveException(Exception ex) {
        super(ex);
    }

}
