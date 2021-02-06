package com.soap.moon.domains.user.repository;

import com.soap.moon.domains.user.domain.UserOauth;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOauthRepository extends JpaRepository<UserOauth, Long> {
    Optional<UserOauth> findByEmail(String email);
}
