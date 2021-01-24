package com.soap.moon.domains.member.exception;

import com.soap.moon.global.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ErrorExceptionController {

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

        log.info("customJwtRuntimeException", ex);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.INVALID_JWT_TOKEN.getCode())
                .message(ex.getMessage()).build(),
            HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = CustomAuthenticationException.class)
    public ResponseEntity<?> customAuthenticationException(CustomAuthenticationException ex) {

        log.info("customAuthenticationException", ex);

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(ex.getMessage()).build(),
            HttpStatus.UNAUTHORIZED);
    }
}
