package com.soap.moon.domains.user.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.soap.moon.ControllerTest;
import com.soap.moon.domains.product.service.ProductService;
import com.soap.moon.domains.user.domain.Token;
import com.soap.moon.domains.user.dto.LoginDto.LoginReq;
import com.soap.moon.domains.user.dto.LoginDto.LogoutReq;
import com.soap.moon.domains.user.dto.LoginDto.RefreshReq;
import com.soap.moon.domains.user.service.LoginService;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class LoginControllerTest extends ControllerTest {

    private static final String LOGIN_API_URL = "/api/v1";

    @MockBean
    LoginService loginService;

    @DisplayName("로그인")
    @Test
    void 로그인() throws Exception {
        String content = objectMapper.writeValueAsString(
            LoginReq.builder().email("test@test.com").password("1q2w3e4r1!").build()
        );
        Map<String, Object> map = new HashMap<>();
        map.put(Token.ACCESS_TOKEN.getName(), "access_token");
        map.put(Token.REFRESH_TOKEN.getName(), "refresh_token");

        when(loginService.login(any())).thenReturn(map);
        createOrReadByJsonParams(LOGIN_API_URL + "/login", content,
            jsonPath("$.data.*", hasSize(3))); //is : equalTo
    }

    @DisplayName(" 로그아웃")
    @Test
    void 로그아웃() throws Exception{
        String content = objectMapper.writeValueAsString(
            LogoutReq.builder().accessToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJub3NlbGxAbm9zZWxsLmNvbSIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2MTQ3NjM4ODd9.If8pyaec_UHS4Rbo9TkhNfJQ_mUVj62lBNoXesuYWYCr8OIDk5YnIc7Yw4-RDDbHsZKy57Jt5U6VgRq3g6ZmwQ").build()
        );
        createOrReadByJsonParams(LOGIN_API_URL + "/logout", content,
            jsonPath("$.data.*", hasSize(1))); //is : equalTo
    }

    @DisplayName("refresh_tokne을 이용한 new access_token 요청")
    @Test
    void 새로운_액세스_토큰() throws Exception {
        String content = objectMapper.writeValueAsString(
            RefreshReq.builder().accessToken("access_token").refreshToken("refresh_token").build()
        );
        RefreshReq access_token = RefreshReq.builder().accessToken("access_token").build();
        when(loginService.provideNewAccessToken(any())).thenReturn("access_token");
        createOrReadByJsonParams(LOGIN_API_URL + "/refresh", content,
            jsonPath("$.data.*", hasSize(1)));
    }

}