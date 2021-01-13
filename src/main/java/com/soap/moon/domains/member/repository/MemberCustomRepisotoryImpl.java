package com.soap.moon.domains.member.repository;

import com.soap.moon.domains.member.domain.Member;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

//복잡한 조회용 쿼리
@Transactional(readOnly = true)
public class MemberCustomRepisotoryImpl extends QuerydslRepositorySupport implements
    MemberCustomRepository {

    public MemberCustomRepisotoryImpl() {
        super(Member.class);
    }


}
