package com.soap.moon.domains.member.repository;

import com.soap.moon.domains.member.domain.Account;
import com.soap.moon.domains.member.dto.query.MemberAuthorityDto;

public interface MemberRepositoryCustom {
    MemberAuthorityDto findOneWithAuthoritiesByAccount(Account account);
}
