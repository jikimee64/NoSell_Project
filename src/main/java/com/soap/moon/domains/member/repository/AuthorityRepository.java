package com.soap.moon.domains.member.repository;

import com.soap.moon.domains.member.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
