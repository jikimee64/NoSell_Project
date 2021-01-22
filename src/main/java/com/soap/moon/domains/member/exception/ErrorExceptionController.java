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

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<?> memberDuplicationException(MemberDuplicationException ex) {
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code(ErrorCode.EMAIL_DUPLICATION.getCode())
                .message(ErrorCode.EMAIL_DUPLICATION.getMessage()).build(),
            HttpStatus.BAD_REQUEST);
    }
}
