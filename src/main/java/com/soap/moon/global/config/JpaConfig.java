package com.soap.moon.global.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider(){
        return () -> Optional.of("사용자 아이디");
    }

}
