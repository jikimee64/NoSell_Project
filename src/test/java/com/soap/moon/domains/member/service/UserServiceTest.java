package com.soap.moon.domains.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.soap.moon.domains.member.domain.User;
import com.soap.moon.domains.member.dto.UserDto.SignInReq;
import com.soap.moon.domains.member.exception.MemberDuplicationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.config.location=" +
    "classpath:/application.yml" +
    ",classpath:/application-secret.yml")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("회원가입")
    void register() {

        SignInReq dto = SignInReq.builder()
            .email("admin@admin.com")
            .password("password")
            .phoneNum("01099991111")
            .nickName("name")
            .build();

        User save = userService.save(dto);
        assertThat(dto.getEmail()).isEqualTo(save.getAccount().getEmail());
    }

    @Test
    @DisplayName("회원가입 중복 검증")
    void Duplicate_Verification() {
        SignInReq dto = SignInReq.builder()
            .email("admin@admin.com")
            .password("password")
            .phoneNum("01099991111")
            .nickName("name")
            .build();

        SignInReq dto2 = SignInReq.builder()
            .email("admin@admin.com")
            .password("password")
            .phoneNum("01099991111")
            .nickName("name")
            .build();

        User save = userService.save(dto);

        Assertions.assertThrows(MemberDuplicationException.class, () -> {
            userService.save(dto2);
        });
    }


}