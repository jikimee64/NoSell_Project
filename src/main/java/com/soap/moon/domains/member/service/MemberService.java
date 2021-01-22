package com.soap.moon.domains.member.service;

import com.soap.moon.domains.member.domain.Account;
import com.soap.moon.domains.member.domain.Authority;
import com.soap.moon.domains.member.domain.Member;
import com.soap.moon.domains.member.domain.MemberAuthority;
import com.soap.moon.domains.member.domain.MemberStatus;
import com.soap.moon.domains.member.domain.Password;
import com.soap.moon.domains.member.dto.MemberDto;
import com.soap.moon.domains.member.exception.MemberDuplicationException;
import com.soap.moon.domains.member.repository.AuthorityRepository;
import com.soap.moon.domains.member.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    /**
     * 회원가입
     */
    @Transactional
    public Member save(MemberDto.SignInReq dto){
        validateDuplicateMember(dto.getUserId());

        Account account = Account.builder().userId(dto.getUserId()).build();
        Password password = Password.builder()
            .password(passwordEncoder.encode(dto.getPassword()))
            .build();

        Optional<Authority> authorityRoleUser = authorityRepository.findById(1L);

        Member member = Member.builder()
            .account(account)
            .password(password)
            .name(dto.getName())
            .status(MemberStatus.ACTIVE)
            .lastLoginAt(LocalDateTime.now())
            .build();

        MemberAuthority memberAuthority = MemberAuthority.builder()
            .member(member)
            .authority(authorityRoleUser.get())
            .build();

        member.addAuthority(memberAuthority);
        return memberRepository.save(member);
    }

    /**
     * 중복회원 체크
     * account컬럼 유니크 제약조건 추가
     */
    private void validateDuplicateMember(String userId) {
        Account account = Account.builder().userId(userId).build();
        Optional<Member> findMember = memberRepository.findByAccount(account);
        findMember.ifPresent(fm -> {
            throw new MemberDuplicationException();
        });
    }

    /**
     * 회원 1명 조회
     */
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

}
