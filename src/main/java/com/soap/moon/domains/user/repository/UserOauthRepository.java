package com.soap.moon.domains.user.repository;

import com.soap.moon.domains.user.domain.UserOauth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOauthRepository extends JpaRepository<UserOauth, Long> {
}
