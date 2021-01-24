package com.soap.moon.domains.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//복잡한 조회용 쿼리
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

//    @Override
//    public MemberAuthorityDto findOneWithAuthoritiesByAccount(Account account) {
//        return queryFactory
//            .select(
//                new QMemberAuthorityDto(
//                    member.account,
//                    member.password,
//                    member.name,
//                    member.status,
//                    memberAuthority.authority.authorityName
//                    ))
//            .from(member)
//            .leftJoin(member.authorities, memberAuthority)
//            .where(member.account.eq(account))
//            .fetchOne();
//    }
}
