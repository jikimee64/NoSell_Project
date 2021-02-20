package com.soap.moon.domains.user.repository;

import com.soap.moon.domains.user.domain.Account;
import com.soap.moon.domains.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByAccount(Account account);

    @Query("select m from User m left join fetch m.authorities where m.account.email= :email")
    Optional<User> findOneWithAuthoritiesByAccount(@Param("email") String email);

//    @Query("select m from User m left join fetch m. where m.id= :userId")
//    Optional<User> findUserWithUserReview(@Param("userId") Long userId);



}
