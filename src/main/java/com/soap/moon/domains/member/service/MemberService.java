package com.soap.moon.domains.member.service;

import com.soap.moon.domains.member.domain.Member;
import com.soap.moon.domains.member.exception.MemberDuplicationException;
import com.soap.moon.domains.member.repository.MemberCustomRepository;
import com.soap.moon.domains.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberCustomRepository memberCustomRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long save(Member member){
        validateDupicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복회원 체크
     * account컬럼 유니크 제약조건 추가
     */
    private void validateDupicateMember(Member member) {
        Optional<Member> findMember = memberRepository.findByAccount(member.getAccount());
        findMember.ifPresent(fm -> {
            throw new MemberDuplicationException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 회원 1명 조회
     */
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

}
