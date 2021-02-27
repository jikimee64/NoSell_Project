package com.soap.moon.global.error;

import com.soap.moon.domains.product.exception.ProductNotFoundException;
import com.soap.moon.domains.user.exception.CustomAuthenticationException;
import com.soap.moon.domains.user.exception.JwtExpiredException;
import com.soap.moon.domains.user.exception.JwtMalFormedException;
import com.soap.moon.domains.user.exception.JwtUnsupportedException;
import com.soap.moon.domains.user.exception.LoginFailedException;
import com.soap.moon.domains.user.exception.MemberDuplicationException;
import com.soap.moon.domains.user.exception.MemberLogoutException;
import com.soap.moon.domains.user.exception.MemberNotFoundException;
import com.soap.moon.domains.user.exception.MemberStatusInActiveException;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.global.common.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
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
            ErrorResponse.builder()
                .code("RunTime")
                .message("알수없는 런타임 에러입니다.")
                .status(000).build(),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MemberDuplicationException.class)
    public ResponseEntity<?> memberDuplicationException(MemberDuplicationException ex) {
        log.info("memberDuplicationException");
        return new ResponseEntity<>(
            ErrorResponse.builder()
                .code(ErrorCode.EMAIL_DUPLICATION.getCode())
                .message(ex.getMessage())
                .status(ErrorCode.EMAIL_DUPLICATION.getStatus()).build(),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MemberStatusInActiveException.class)
    public ResponseEntity<?> memberStatusInActiveException(MemberStatusInActiveException ex) {

        log.info("memberStatusInActiveException", ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .code(ErrorCode.MEMBER_DUPLICATION.getCode())
                .message(ex.getMessage())
                .status(ErrorCode.MEMBER_DUPLICATION.getStatus()).build(),
            HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = MemberNotFoundException.class)
    public ResponseEntity<?> memberNotFoundException(MemberNotFoundException ex) {

        log.info("memberNotFoundException", ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .code(ErrorCode.MEMBER_NOT_FOUND.getCode())
                .message(ex.getMessage())
                .status(ErrorCode.MEMBER_NOT_FOUND.getStatus()).build(),
            HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = MemberLogoutException.class)
    public ResponseEntity<?> memberLogoutException(MemberLogoutException ex) {

        log.info("memberLogoutException", ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .code(ErrorCode.MEMBER_LOGOUT.getCode())
                .message(ex.getMessage())
                .status(ErrorCode.MEMBER_LOGOUT.getStatus()).build(),
            HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = CustomAuthenticationException.class)
    public ResponseEntity<?> customAuthenticationException(CustomAuthenticationException ex) {

        log.info("customAuthenticationException", ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(ex.getMessage())
                .status(ErrorCode.AUTHENTICATION_FAILED.getStatus())
                .build(),
            HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = LoginFailedException.class)
    public ResponseEntity<?> loginFailedException(LoginFailedException ex) {

        log.info("LoginFailedException", ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .code(ErrorCode.LOGIN_FAILED.getCode())
                .message(ex.getMessage())
                .status(ErrorCode.LOGIN_FAILED.getStatus())
                .build(),
            HttpStatus.UNAUTHORIZED);
    }

    //시큐리티의 authenticate()에서 비밀번호가 다르거나 존재하지 않을 떄 발생
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {

        log.info("handleBadCredentialsException", ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(ex.getMessage())
                .status(ErrorCode.AUTHENTICATION_FAILED.getStatus())
                .build(),
            HttpStatus.UNAUTHORIZED);
    }

    //만료 시간이 지난 인증 통큰 요청시...
    @ExceptionHandler(JwtExpiredException.class)
    protected ResponseEntity<?> jwtExpiredException(JwtExpiredException ex) {
        log.info("jwtExpiredException", ex);
        return new ResponseEntity<>(
            ErrorResponse.builder()
                .code(ErrorCode.EXPIRED_JWT_TOKE.getCode())
                .message(ex.getMessage())
                .status(ErrorCode.EXPIRED_JWT_TOKE.getStatus())
                .build(),
        HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtUnsupportedException.class)
    protected ResponseEntity<?> jwtUnsupportedException(JwtUnsupportedException ex) {
        log.info("jwtUnsupportedException", ex);
        return new ResponseEntity<>(
            ErrorResponse.builder()
                .code(ErrorCode.NOT_APPLY_JWT.getCode())
                .message(ex.getMessage())
                .status(ErrorCode.NOT_APPLY_JWT.getStatus())
                .build(),
            HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtMalFormedException.class)
    protected ResponseEntity<?> jwtMalFormedException(JwtMalFormedException ex) {
        log.info("jwtMalFormedException", ex);
        return new ResponseEntity<>(
            ErrorResponse.builder()
                .code(ErrorCode.NOT_FORM_JWT.getCode())
                .message(ex.getMessage())
                .status(ErrorCode.NOT_FORM_JWT.getStatus())
                .build(),
            HttpStatus.UNAUTHORIZED);
    }

    //상품이 존재하지 않을 경우
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<?> productNotFoundException(ProductNotFoundException ex) {

        log.info("productNotFoundException", ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .code(ErrorCode.PRODUCT_NOT_FOUND.getCode())
                .message(ex.getMessage())
                .status(ErrorCode.PRODUCT_NOT_FOUND.getStatus()).build(),
            HttpStatus.NOT_FOUND);
    }

}
