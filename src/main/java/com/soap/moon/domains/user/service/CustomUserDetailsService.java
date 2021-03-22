package com.soap.moon.domains.user.service;

import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.domain.UserStatus;
import com.soap.moon.domains.user.exception.MemberStatusNotActiveException;
import com.soap.moon.domains.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) { //username : 아이디
        // Account account = Account.builder().email(username).build();

        return userRepository.findOneWithAuthoritiesByAccount(username)
            .map(member -> createMember(username, member))
            .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createMember(String username,
        User user) {
        if (!user.getStatus().equals(UserStatus.ACTIVE)) {
            throw new MemberStatusNotActiveException();
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(
                authority.getAuthority().getAuthorityName()))
            .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getAccount().getEmail(),
            user.getPassword().getPassword(),
            grantedAuthorities);
    }
}
