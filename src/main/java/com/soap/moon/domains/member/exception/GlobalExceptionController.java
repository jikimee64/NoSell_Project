package com.soap.moon.domains.member.exception;

import com.soap.moon.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionController {

    @ExceptionHandler(value = MemberDuplicationException.class)
    public ResponseEntity<?> memberDuplicationException(MemberDuplicationException ex) {

        log.info("memberDuplicationException");


        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.EMAIL_DUPLICATION.getCode())
                .message(ex.getMessage()).build(),
            HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MemberStatusInActiveException.class)
    public ResponseEntity<?> memberStatusInActiveException(MemberStatusInActiveException ex) {

        log.info("memberStatusInActiveException", ex);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.MEMBER_DUPLICATION.getCode())
                .message(ex.getMessage()).build(),
            HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = MemberNotFoundException.class)
    public ResponseEntity<?> memberNotFoundException(MemberNotFoundException ex) {

        log.info("memberNotFoundException", ex);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.MEMBER_NOT_FOUND.getCode())
                .message(ex.getMessage()).build(),
            HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = CustomJwtRuntimeException.class)
    public ResponseEntity<?> customJwtRuntimeException(CustomJwtRuntimeException ex) {

        log.info("========== customJwtRuntimeException ==========", ex);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.INVALID_JWT_TOKEN.getCode())
                .message(ex.getMessage())
                .data(ErrorCode.INVALID_JWT_TOKEN.getStatus())
                .build(),
            HttpStatus.UNAUTHORIZED);
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

//    @ExceptionHandler(CustomAuthenticationException.class)
//    protected ResponseEntity<?> handleCustomAuthenticationException(CustomAuthenticationException ex) {
//
//        log.info("handleCustomAuthenticationException", ex);
//
//        return new ResponseEntity<>(
//            CommonResponse.builder()
//                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
//                .message(ex.getMessage()).build(),
//            HttpStatus.UNAUTHORIZED);
//    }

    //시큐리티의 authenticate()에서 비밀번호가 다를때 발생
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
}
