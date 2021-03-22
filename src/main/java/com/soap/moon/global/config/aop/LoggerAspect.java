package com.soap.moon.global.config.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @PerformanceTimeRecord 가 적용된 메소드 Aspect 실행
 */
@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("@annotation(com.soap.moon.global.config.aop.PerformanceTimeRecord)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
            .currentRequestAttributes()).getResponse();
        String userIp = request.getRemoteAddr();

        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        //log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        log.info("[Process-Result]: {} || {} || {} || {} || {} ms",
            userIp,
            request.getRequestURI(),
            request.getMethod(),
            response.getStatus(),
            executionTime);

        return proceed;
    }
}
