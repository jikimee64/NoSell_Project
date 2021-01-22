package com.soap.moon.global.config.security;

import com.soap.moon.infra.jwt.JwtAccessDeniedHandler;
import com.soap.moon.infra.jwt.JwtAuthenticationEntryPoint;
import com.soap.moon.infra.jwt.JwtSecurityConfig;
import com.soap.moon.infra.jwt.JwtTokenProvider;
import io.swagger.models.Swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(
        JwtTokenProvider tokenProvider,
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
        JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .disable()
            // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
            .csrf().disable() //csrf 방지

            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            // enable h2-console
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            // 세션을 사용하지 않기 때문에 STATELESS로 설정
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .antMatchers("/api/v1/member/signin").permitAll()
            .antMatchers("/api/v1/member/signup").permitAll()


            .and()
            .apply(new JwtSecurityConfig(tokenProvider));
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //해당 경로의 파일들은 Spring Security가 무시할 수 있도록 설정

        // Swagger API 사용하기 위한설정
        web
            .ignoring()
            .antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"
            );

        // h2-console을 사용하기 위한설정
        web
            .ignoring()
            .antMatchers(
                "/h2-console/**",
                "/favicon.ico"

            );
    }
}

