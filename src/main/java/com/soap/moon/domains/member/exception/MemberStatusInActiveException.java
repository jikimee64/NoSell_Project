package com.soap.moon.domains.member.exception;

import com.soap.moon.domains.member.domain.MemberStatus;

public class MemberStatusInActiveException extends RuntimeException {

    public MemberStatusInActiveException() {
        super(MemberStatus.INACTIVE.getTitle());
    }

    public MemberStatusInActiveException(Exception ex) {
        super(ex);
    }

}
