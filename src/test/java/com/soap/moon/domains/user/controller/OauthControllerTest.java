package com.soap.moon.domains.user.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import com.soap.moon.ControllerTest;
import com.soap.moon.domains.user.domain.Account;
import com.soap.moon.domains.user.domain.Password;
import com.soap.moon.domains.user.domain.ProviderType;
import com.soap.moon.domains.user.domain.Token;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.domain.UserStatus;
import com.soap.moon.domains.user.dto.UserDto.CheckUserAuthRes;
import com.soap.moon.domains.user.dto.UserDto.SignInReq;
import com.soap.moon.domains.user.service.OauthService;
import com.soap.moon.domains.user.service.UserService;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

class OauthControllerTest extends ControllerTest {

    private static final String OAUTH_API_URL = "/api/v1/oauth";

    @MockBean
    OauthService oauthService;

    @DisplayName("소셜 callback 메소드")
    @Test
    void 소셜_callback_메소드() throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put(Token.ACCESS_TOKEN.getName(), "access_token");
        map.put(Token.REFRESH_TOKEN.getName(), "access_token");
        map.put("nickName", "닉네임");
        map.put("profileImage", "프로필이미지");

        when(oauthService.requestAccessToken(any(), any(), any())).thenReturn(map);
        mockMvc.perform(get(OAUTH_API_URL + "/{socialLoingType}/callback", ProviderType.GOOGLE)
            .param("code", "code")
            .param("state", "state")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(cookie().exists("refreshToken"))
            .andExpect(jsonPath("$.data.*", hasSize(3)))
            .andDo(print());
    }

}