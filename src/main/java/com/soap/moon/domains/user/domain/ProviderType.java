package com.soap.moon.domains.user.domain;

import lombok.Getter;

@Getter
public enum ProviderType {
    GOOGLE("GOOGLE"),
    NAVER("NAVER");

    String name;

    ProviderType(String name) {
        this.name = name;
    }
}
