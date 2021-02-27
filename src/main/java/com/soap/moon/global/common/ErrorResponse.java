package com.soap.moon.global.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    @ApiModelProperty(value = "자체 코드")
    private String code;

    @ApiModelProperty(value = "메시지")
    private String message;

    @ApiModelProperty(value = "HTTP 상태")
    private int status;

}


