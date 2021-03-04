package com.soap.moon.domains.user.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.soap.moon.ControllerTest;
import com.soap.moon.domains.user.domain.Account;
import com.soap.moon.domains.user.domain.Password;
import com.soap.moon.domains.user.domain.Token;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.domain.UserStatus;
import com.soap.moon.domains.user.dto.LoginDto.LoginReq;
import com.soap.moon.domains.user.dto.LoginDto.LogoutReq;
import com.soap.moon.domains.user.dto.LoginDto.RefreshReq;
import com.soap.moon.domains.user.dto.UserDto.CheckUserAuthRes;
import com.soap.moon.domains.user.dto.UserDto.EmailCheckReq;
import com.soap.moon.domains.user.dto.UserDto.PhoneCheckReq;
import com.soap.moon.domains.user.dto.UserDto.SignInReq;
import com.soap.moon.domains.user.service.LoginService;
import com.soap.moon.domains.user.service.UserService;
import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class UserControllerTest extends ControllerTest {

    private static final String USER_API_URL = "/api/v1/users";

    @MockBean
    UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        Account account = Account.builder().email("test@test.com").build();
        Password password = Password.builder()
            .password("test")
            .build();
        user = User.builder()
            .account(account)
            .password(password)
            .nickName("닉네임")
            .phoneNum("핸드폰번호")
            .profileImage("프로필 이미지")
            .status(UserStatus.ACTIVE)
            .build();
    }

    @DisplayName("액세스토큰_유효성검사")
    @Test
    void 액세스토큰_유효성검사() throws Exception {
        CheckUserAuthRes build = CheckUserAuthRes.builder().nickName("닉네임").profileImage("프로필 이미지")
            .build();
        when(userService.checkUserAuth(any())).thenReturn(build);
        readInHeader(USER_API_URL + "/auth",
            jsonPath("$.data.*", hasSize(2))); //is : equalTo
    }

    @DisplayName("회원가입")
    @Test
    void 회원가입() throws Exception {
        String content = objectMapper.writeValueAsString(
            SignInReq.builder().email("test@test.com").password("1q2w3e4r1!").nickName("test")
                .phoneNum("01000000000").build()
        );
        when(userService.save(any())).thenReturn(user);
        createOrReadByJsonParams(USER_API_URL, content,
            jsonPath("$.data.*", hasSize(1))); //is : equalTo
    }

    @DisplayName("이메일 중복검사")
    @Test
    void 이메일_중복검사() throws Exception {
        String content = objectMapper.writeValueAsString(
            EmailCheckReq.builder().email("test@test.com").build()
        );
        when(userService.validateDuplicateMember(any())).thenReturn(true);
        createOrReadByJsonParams(USER_API_URL + "/emailCheck", content,
            jsonPath("$.data.*", hasSize(1))); //is : equalTo
    }

    @DisplayName("휴대폰 인증")
    @Test
    void 휴대폰_인증() throws Exception {
        String content = objectMapper.writeValueAsString(
            PhoneCheckReq.builder().phoneNum("01023160200").build()
        );
        when(userService.certifiedPhoneNumber(any(), any())).thenReturn(true);
        createOrReadByJsonParams(USER_API_URL + "/sendSMS", content,
            jsonPath("$.data.*", hasSize(1))); //is : equalTo
    }

}
