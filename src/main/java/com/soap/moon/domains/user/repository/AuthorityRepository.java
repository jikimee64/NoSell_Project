package com.soap.moon.domains.user.repository;

import com.soap.moon.domains.user.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
