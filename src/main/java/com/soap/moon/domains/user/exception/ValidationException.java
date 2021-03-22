package com.soap.moon.domains.user.exception;

import java.lang.reflect.Field;
import java.util.List;
import lombok.Getter;
import org.mariadb.jdbc.internal.com.send.authentication.ed25519.math.FieldElement;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class ValidationException extends RuntimeException{

    @Getter
    private final List<FieldError> errors;

    public ValidationException(Errors errors){
        this.errors = errors.getFieldErrors();
    }

}
