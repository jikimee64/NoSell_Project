package com.soap.moon.domains.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.persistence.SecondaryTable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class ProductDto {

    @ApiModel("메인 페이지 상품정보")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class mainProductRes{

        //public String profileImage;

        public String name;

        public int price;

        public String dealType;

        public LocalDateTime createdAt;
    }





}
