package com.soap.moon.domains.user.exception;

import com.soap.moon.domains.user.domain.UserStatus;

public class MemberStatusNotActiveException extends RuntimeException {

    public MemberStatusNotActiveException() {
        super(UserStatus.INACTIVE.getTitle());
    }

    public MemberStatusNotActiveException(Exception ex) {
        super(ex);
    }

}
