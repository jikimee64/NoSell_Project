package com.soap.moon.infra.jwt;
import com.soap.moon.domains.member.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //필요한 권한이 없이 접근하려 할때 403

        log.info("=========== JwtAccessDeniedHandler ===========");

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().println("{ \"message\" : \"" + ErrorCode.ACCESS_DENIED.getMessage()
            + "\", \"code\" : \"" +  ErrorCode.ACCESS_DENIED.getCode()
            + "\", \"status\" : " + ErrorCode.ACCESS_DENIED.getStatus()
            + ", \"errors\" : [ ] }");

    }
}