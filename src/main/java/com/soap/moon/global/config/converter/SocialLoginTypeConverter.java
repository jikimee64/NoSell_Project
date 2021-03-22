package com.soap.moon.global.config.converter;

import com.soap.moon.domains.user.domain.ProviderType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class SocialLoginTypeConverter implements Converter<String, ProviderType> {

    @Override
    public ProviderType convert(String s) {
        return ProviderType.valueOf(s.toUpperCase());
    }
}