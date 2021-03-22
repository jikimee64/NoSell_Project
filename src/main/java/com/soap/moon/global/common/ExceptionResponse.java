package com.soap.moon.global.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
public class ExceptionResponse {

    @ApiModelProperty(value = "예외 메시지")
    private final String message;
    @ApiModelProperty(value = "유효성 실패한 값")
    private Object rejectedValue;
    @ApiModelProperty(value = "유효성 실패한 필드명")
    private String field;

    public ExceptionResponse(FieldError fieldError) {
        this.message = fieldError.getDefaultMessage();
        this.field = fieldError.getField();
        this.rejectedValue = fieldError.getRejectedValue();
    }

    public ExceptionResponse(String message) {
        this.message = message;
    }

}


