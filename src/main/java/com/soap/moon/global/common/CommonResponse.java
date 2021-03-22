package com.soap.moon.global.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CommonResponse {

    @ApiModelProperty(value = "상태코드")
    private String code;

    @ApiModelProperty(value = "메시지")
    private String message;

    @ApiModelProperty(value = "데이터")
    private Object data;
}
