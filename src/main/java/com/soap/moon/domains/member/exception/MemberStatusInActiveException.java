package com.soap.moon.domains.member.exception;

import com.soap.moon.domains.member.domain.UserStatus;

public class MemberStatusInActiveException extends RuntimeException {

    public MemberStatusInActiveException() {
        super(UserStatus.INACTIVE.getTitle());
    }

    public MemberStatusInActiveException(Exception ex) {
        super(ex);
    }

}
