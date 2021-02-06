package com.soap.moon.domains.user.domain;

import lombok.Getter;

@Getter
public enum SocialLoginType {
    GOOGLE("GOOGLE"),
    NAVER("NAVER");

    String name;

    SocialLoginType(String name) {
        this.name = name;
    }
}
