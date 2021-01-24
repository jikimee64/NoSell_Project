package com.soap.moon.domains.member.service;

import com.soap.moon.domains.member.domain.Account;
import com.soap.moon.domains.member.domain.Member;
import com.soap.moon.domains.member.domain.MemberStatus;
import com.soap.moon.domains.member.exception.MemberStatusInActiveException;
import com.soap.moon.domains.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
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
public class CustomUserDetailsService implements UserDetailsService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) { //username : 아이디
        Account account = Account.builder().userId(username).build();

        return memberRepository.findOneWithAuthoritiesByAccount(account)
            .map(member -> createMember(username, member))
            .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

        private org.springframework.security.core.userdetails.User createMember(String username, Member member) {
            if (!member.getStatus().equals(MemberStatus.ACTIVE)) {
                throw new MemberStatusInActiveException();
            }
            List<GrantedAuthority> grantedAuthorities = member.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getAuthorityName()))
            .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(member.getAccount().getUserId(),
            member.getPassword().getPassword(),
            grantedAuthorities);
    }
}
