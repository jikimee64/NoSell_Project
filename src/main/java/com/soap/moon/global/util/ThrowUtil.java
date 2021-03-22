package com.soap.moon.global.util;

import com.soap.moon.domains.user.exception.ValidationException;
import org.springframework.validation.Errors;

public interface ThrowUtil {
    public static void hasErrorsThrow(Errors errors){
        if(errors.hasErrors()) {
            throw new ValidationException(errors);
        }
    }
}
