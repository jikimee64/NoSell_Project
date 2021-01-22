package com.soap.moon.domains.member.dto.query;


import com.querydsl.core.annotations.QueryProjection;
import com.soap.moon.domains.member.domain.Account;
import com.soap.moon.domains.member.domain.Authority;
import com.soap.moon.domains.member.domain.MemberAuthority;
import com.soap.moon.domains.member.domain.MemberStatus;
import com.soap.moon.domains.member.domain.Password;
import lombok.Data;

@Data
public class MemberAuthorityDto {
    private Account account;
    private Password password;
    private String name;
    private MemberStatus status;
    private String authorityName;

    @QueryProjection
    public MemberAuthorityDto(Account account, Password password, String name,
        MemberStatus status, String authorityName) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.status = status;
        this.authorityName = authorityName;
    }
}
