package com.soap.moon.domains.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soap.moon.domains.member.domain.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//복잡한 조회용 쿼리
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class MemberRepisotoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

//    @Override
//    public Optional<Member> findOneWithAuthoritiesByAccounte(String account) {
//
//        return null;
//
//    }
}
