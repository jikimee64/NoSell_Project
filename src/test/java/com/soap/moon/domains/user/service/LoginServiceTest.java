package com.soap.moon.domains.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.soap.moon.domains.user.domain.Role;
import org.junit.jupiter.api.DisplayName;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import com.soap.moon.domains.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest(properties = "spring.config.location=" +
    "classpath:/application.yml" +
    ",classpath:/application-secret.yml")
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

//    @Test
//    void loginSuccessTest(){
//
//        String expectedEmail = "nosell@nosell.com";
//        String expectedRole = Role.USER.getCode();
//
//        loginService.login(UserDto.LoginReq.builder().email(expectedEmail).password("nosell").build());
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        assertThat(expectedEmail).isEqualTo( ((User)authentication.getPrincipal()).getUsername());
//        assertThat(expectedRole).isEqualTo( authentication.getAuthorities().stream().findFirst().get().toString());
//    }

    //@Test
//    @DisplayName("비밀번호가 다르거나 아이디가 존재하지 않을 때 BadCredentialsException(자격 증명에 실패하였습니다.) 발생")
//    void loginFailedTest(){
//
//        String expectedEmail = "nosell@nosell.com";
//
//        assertThrows(BadCredentialsException.class, () -> {
//            loginService.login(UserDto.LoginReq.builder().email(expectedEmail).password("password").build());
//        });
//    }
}