package com.soap.moon.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
import lombok.Getter;


@Getter
public class ResponseTemplate<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private ResponseTemplate() {
    }

    private ResponseTemplate(final T data) {
        this.data = data;
    }

    public static ResponseTemplate create() {
        return new ResponseTemplate();
    }

    public static <T> ResponseTemplate<T> create(final T data) {
        return new ResponseTemplate<>(data);
    }

}