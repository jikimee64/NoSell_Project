package com.soap.moon.domains.member.domain;

import com.querydsl.core.support.QueryMixin.Role;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum RoleStatus {
    ADMIN("ROLE_ADMIN", "관리자권한"),
    USER("ROLE_USER", "사용자권한"),
    UNKNOWN("UNKNOWN", "알수없는권한");

    private String code;
    private String description;

    RoleStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static RoleStatus of(String code) {
        return Arrays.stream(RoleStatus.values())
            .filter(r -> r.getCode().equals(code))
            .findAny()
            .orElse(UNKNOWN);
    }
}
