package com.soap.moon.domains.member.domain;

import lombok.Getter;

@Getter
public enum MemberStatus {
    ACTIVE("활동 상태"),
    INACTIVE("정지 상태");

    private String title;

    MemberStatus(String title) {
        this.title = title;
    }
}
