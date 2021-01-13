package com.soap.moon.domains.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleStatus {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String Role;
}
