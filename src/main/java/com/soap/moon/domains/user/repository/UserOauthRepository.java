package com.soap.moon.domains.user.repository;

import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.domain.UserOauth;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserOauthRepository extends JpaRepository<UserOauth, Long> {

    Optional<UserOauth> findByUser(User user);
}
