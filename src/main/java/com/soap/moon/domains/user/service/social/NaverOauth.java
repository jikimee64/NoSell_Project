package com.soap.moon.domains.user.service.social;

import com.soap.moon.domains.user.dto.GoogleAuthDto;
import com.soap.moon.domains.user.dto.GoogleAuthDto.TokenRes;
import org.springframework.stereotype.Component;

@Component
public class NaverOauth implements SocialOauth {

    @Override
    public String getOauthRedirectURL() {
        return "";
    }

    @Override
    public TokenRes requestAccessToken(String code) {
        return null;
    }
}