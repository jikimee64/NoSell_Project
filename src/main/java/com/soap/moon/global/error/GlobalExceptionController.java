package com.soap.moon.global.error;

import com.soap.moon.domains.user.exception.CustomAuthenticationException;
import com.soap.moon.domains.user.exception.LoginFailedException;
import com.soap.moon.domains.user.exception.MemberDuplicationException;
import com.soap.moon.domains.user.exception.MemberNotFoundException;
import com.soap.moon.domains.user.exception.MemberStatusInActiveException;
import com.soap.moon.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionController {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> runtimeException(RuntimeException ex) {
        log.info("RuntimeException");
        ex.printStackTrace();
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("RunTime")
                .message("알수없는 런타임 에러입니다.")
                .data("none").build(),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MemberDuplicationException.class)
    public ResponseEntity<?> memberDuplicationException(MemberDuplicationException ex) {
        log.info("memberDuplicationException");
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.EMAIL_DUPLICATION.getCode())
                .message(ex.getMessage())
                .data(ErrorCode.EMAIL_DUPLICATION.getStatus()).build(),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MemberStatusInActiveException.class)
    public ResponseEntity<?> memberStatusInActiveException(MemberStatusInActiveException ex) {

        log.info("memberStatusInActiveException", ex);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.MEMBER_DUPLICATION.getCode())
                .message(ex.getMessage())
                .data(ErrorCode.MEMBER_DUPLICATION.getStatus()).build(),
            HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = MemberNotFoundException.class)
    public ResponseEntity<?> memberNotFoundException(MemberNotFoundException ex) {

        log.info("memberNotFoundException", ex);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.MEMBER_NOT_FOUND.getCode())
                .message(ex.getMessage())
                .data(ErrorCode.MEMBER_NOT_FOUND.getStatus()).build(),
            HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = CustomAuthenticationException.class)
    public ResponseEntity<?> customAuthenticationException(CustomAuthenticationException ex) {

        log.info("customAuthenticationException", ex);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(ex.getMessage())
                .data(ErrorCode.AUTHENTICATION_FAILED.getStatus())
                .build(),
            HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = LoginFailedException.class)
    public ResponseEntity<?> loginFailedException(LoginFailedException ex) {

        log.info("LoginFailedException", ex);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.LOGIN_FAILED.getCode())
                .message(ex.getMessage())
                .data(ErrorCode.LOGIN_FAILED.getStatus())
                .build(),
            HttpStatus.UNAUTHORIZED);
    }

    //시큐리티의 authenticate()에서 비밀번호가 다르거나 존재하지 않을 떄 발생
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<CommonResponse> handleBadCredentialsException(BadCredentialsException ex) {

        log.info("handleBadCredentialsException", ex);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(ex.getMessage())
                .data(ErrorCode.AUTHENTICATION_FAILED.getStatus())
                .build(),
            HttpStatus.UNAUTHORIZED);
    }

    //만료 시간이 지난 인증 통큰 요청시...
    @ExceptionHandler(InsufficientAuthenticationException.class)
    protected ResponseEntity<?> handleInsufficientAuthenticationException(InsufficientAuthenticationException e) {

        log.info("handleInsufficientAuthenticationException", e);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(ErrorCode.AUTHENTICATION_FAILED.getMessage())
                .data(ErrorCode.AUTHENTICATION_FAILED.getStatus())
                .build(),
        HttpStatus.UNAUTHORIZED);
    }
}
