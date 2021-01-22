package com.soap.moon.domains.member.repository;

import com.soap.moon.domains.member.domain.Account;
import com.soap.moon.domains.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
    public Optional<Member> findByAccount(Account account);
}
