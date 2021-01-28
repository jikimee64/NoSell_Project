package com.soap.moon.domains.member.domain;

import lombok.Getter;

@Getter
public enum UserStatus {
    ACTIVE("활동 상태"),
    INACTIVE("정지 상태");

    private String title;

    UserStatus(String title) {
        this.title = title;
    }
}
