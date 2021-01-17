package com.soap.moon.global.common;

import com.soap.moon.domains.member.exception.ErrorCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonResponse {

    @ApiModelProperty(value="상태코드")
    private String code;

    @ApiModelProperty(value="메시지")
    private String message;

    @ApiModelProperty(value="데이터")
    private Object data;

}
