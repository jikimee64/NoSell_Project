package com.soap.moon.global.error;

import com.soap.moon.domains.product.exception.ProductNotFoundException;
import com.soap.moon.domains.user.exception.JwtExpiredException;
import com.soap.moon.domains.user.exception.JwtMalFormedException;
import com.soap.moon.domains.user.exception.JwtUnsupportedException;
import com.soap.moon.domains.user.exception.MemberDuplicationException;
import com.soap.moon.domains.user.exception.MemberLogoutException;
import com.soap.moon.domains.user.exception.MemberNotFoundException;
import com.soap.moon.domains.user.exception.MemberStatusNotActiveException;
import com.soap.moon.domains.user.exception.ValidationException;
import com.soap.moon.global.common.ExceptionResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionController {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage());
        //ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ErrorCode.RUNTIME_EXCEPTION.getMessage());
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
//        log.error(ex.getMessage());
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return new ResponseEntity<>(
//            ExceptionResponse.builder()
//                .message(errors).build(),
//            HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(value = MemberDuplicationException.class)
    public ResponseEntity<?> handleMemberDuplicationException(MemberDuplicationException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ExceptionResponse(ErrorCode.EMAIL_DUPLICATION.getMessage()));
    }

    /**
     * 회원 계정 정지 상태
     */
    @ExceptionHandler(value = MemberStatusNotActiveException.class)
    public ResponseEntity<?> handleMemberStatusNotActiveException(
        MemberStatusNotActiveException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(ErrorCode.MEMBER_NOT_ACTIVE.getMessage());
    }

    @ExceptionHandler(value = MemberNotFoundException.class)
    public ResponseEntity<?> handleMemberNotFoundException(MemberNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorCode.MEMBER_NOT_FOUND.getMessage());
    }

    @ExceptionHandler(value = MemberLogoutException.class)
    public ResponseEntity<?> handleLogoutException(MemberLogoutException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorCode.MEMBER_LOGOUT.getMessage());
    }

//    @ExceptionHandler(value = ValidationException.class)
//    public ResponseEntity<?> handleValidationException(ValidationException ex){
//        log.error(ex.getMessage());
//        List<ExceptionResponse> responses = ex.getErrors().stream().map(ExceptionResponse::new).collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//            .body(responses);
//    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException ex) {
        log.error(ex.getMessage());
        List<ExceptionResponse> responses = ex.getErrors().stream().map(ExceptionResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(responses.get(0));
    }

    /**
     * 시큐리티의 authenticate()에서 아이디 혹은 비밀번호가 다르거나 존재하지 않을 떄 발생
     */
    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<?> handleBadCredentialsException(BadCredentialsException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorCode.LOGIN_FAILED.getMessage());
    }

    @ExceptionHandler(JwtExpiredException.class)
    protected ResponseEntity<?> handleJwtExpiredException(JwtExpiredException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorCode.EXPIRED_JWT_TOKEN.getMessage());
    }

    @ExceptionHandler(JwtUnsupportedException.class)
    protected ResponseEntity<?> handleJwtUnsupportedException(JwtUnsupportedException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorCode.NOT_APPLY_JWT.getMessage());
    }

    @ExceptionHandler(JwtMalFormedException.class)
    protected ResponseEntity<?> handleJwtMalFormedException(JwtMalFormedException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorCode.NOT_FORM_JWT.getMessage());
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorCode.PRODUCT_NOT_FOUND.getMessage());
    }

}
