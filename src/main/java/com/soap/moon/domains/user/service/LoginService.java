package com.soap.moon.domains.user.service;

import com.soap.moon.domains.category.repository.CategoryUserRepository;
import com.soap.moon.domains.user.domain.Account;
import com.soap.moon.domains.user.domain.Token;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.dto.LoginDto;
import com.soap.moon.domains.user.dto.UserDto;
import com.soap.moon.domains.user.repository.UserRepository;
import com.soap.moon.global.jwt.JwtTokenProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public Map<String, Object> login(LoginDto.LoginReq loginDto) {

        Map<String, Object> map = new HashMap<>();

        //아이디와 패스워드를 조합해서 인스턴스 생성
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        // authenticate() 실행시 loadUserByUsername 실행
        // 사용자 비밀번호 체크, 패스워드 일치하지 않는다면 Exception 발생 및 이후 로직 실행 안됨
        Authentication authentication = authenticationManagerBuilder.getObject()
            .authenticate(authenticationToken);

        //로그인 성공하면 인증 객체 생성 및 스프링 시큐리티 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);

        map.put(Token.ACCESS_TOKEN.getName(), jwtTokenProvider.createToken(authentication));

        Account account = Account.builder().email(loginDto.getEmail()).build();
        Optional<User> byAccount = userRepository.findByAccount(account);
        map.put("id", byAccount.get().getId());

        return map;

    }
}
