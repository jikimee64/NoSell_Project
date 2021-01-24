package com.soap.moon.domains.member.repository;

import com.soap.moon.domains.member.domain.Account;
import com.soap.moon.domains.member.domain.Member;
import java.util.Optional;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom{
    public Optional<Member> findByAccount(Account account);

    @Query("select m from Member m left join fetch m.authorities")
    Optional<Member> findOneWithAuthoritiesByAccount(Account account);

}
