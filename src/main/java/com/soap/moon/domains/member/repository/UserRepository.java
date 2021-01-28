package com.soap.moon.domains.member.repository;

import com.soap.moon.domains.member.domain.Account;
import com.soap.moon.domains.member.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    public Optional<User> findByAccount(Account account);

    @Query("select m from User m left join fetch m.authorities")
    Optional<User> findOneWithAuthoritiesByAccount(Account account);

}
