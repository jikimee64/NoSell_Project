package com.soap.moon.infra.jwt;

import com.soap.moon.domains.member.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException) throws IOException {
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401

        String exception = (String)request.getAttribute("exception");
        ErrorCode errorCode;

        log.debug("log: exception: {} ", exception);

        /**
         * 토큰 없는 경우
         */
        if(exception == null) {
            errorCode = ErrorCode.NON_LOGIN;
            setResponse(response, errorCode);
            return;
        }

        /**
         * 토큰 만료된 경우
         */
        if(exception.equals(ErrorCode.EXPIRED_JWT_TOKE.getCode())) {
            errorCode = ErrorCode.EXPIRED_JWT_TOKE;
            setResponse(response, errorCode);
            return;
        }

        /**
         * 토큰 시그니처가 다른 경우
         */
        if(exception.equals(ErrorCode.INVALID_JWT_TOKEN.getCode())) {
            errorCode = ErrorCode.INVALID_JWT_TOKEN;
            setResponse(response, errorCode);
        }

        log.info("JwtAuthenticationEntryPoint");

    }

    /**
     * 한글 출력을 위해 getWriter() 사용
     */
    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println("{ \"message\" : \"" + errorCode.getMessage()
            + "\", \"code\" : \"" +  errorCode.getCode()
            + "\", \"status\" : " + errorCode.getStatus()
            + ", \"errors\" : [ ] }");
    }

}