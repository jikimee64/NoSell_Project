package com.soap.moon.domains.user.service;

import com.soap.moon.domains.user.domain.Account;
import com.soap.moon.domains.user.domain.Authority;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.domain.UserAuthority;
import com.soap.moon.domains.user.domain.UserStatus;
import com.soap.moon.domains.user.domain.Password;
import com.soap.moon.domains.user.dto.UserDto;
import com.soap.moon.domains.user.exception.MemberDuplicationException;
import com.soap.moon.domains.user.repository.AuthorityRepository;
import com.soap.moon.domains.user.repository.UserRepository;
import com.soap.moon.global.util.SecurityUtil;
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
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    @Transactional
    public User save(UserDto.SignInReq dto) {
        validateDuplicateMember(dto.getEmail());

        Account account = Account.builder().email(dto.getEmail()).build();
        Password password = Password.builder()
            .password(passwordEncoder.encode(dto.getPassword()))
            .build();

        //ROLE_USER GET
        Optional<Authority> authorityRoleUser = authorityRepository.findById(1L);

        User user = User.builder()
            .account(account)
            .password(password)
            .nickName(dto.getNickName())
            .phoneNum(dto.getPhoneNum())
            .profileImage("https://user-images.githubusercontent.com/52563841/108304539-9a01e200-71eb-11eb-94a7-01ead35e186e.png")
            .status(UserStatus.ACTIVE)
            .lastLoginAt(LocalDateTime.now())
            .build();

        UserAuthority userAuthority = UserAuthority.builder()
            .user(user)
            .authority(authorityRoleUser.get())
            .build();

        user.addAuthority(userAuthority);
        return userRepository.save(user);
    }

    /**
     * 중복회원 체크 account컬럼 유니크 제약조건 추가
     */
    private void validateDuplicateMember(String userId) {
        Optional<User> findMember = userRepository.findByAccount(getAccountByUserId(userId));
        findMember.ifPresent(fm -> {
            throw new MemberDuplicationException();
        });
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String userId) {
        return userRepository.findOneWithAuthoritiesByAccount(userId);
    }

    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
            .flatMap(s -> userRepository.findOneWithAuthoritiesByAccount(s));
    }

    private Account getAccountByUserId(String userId) {
        return Account.builder().email(userId).build();
    }

}
