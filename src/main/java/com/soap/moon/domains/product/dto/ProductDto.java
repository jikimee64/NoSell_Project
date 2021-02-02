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
        @ApiModelProperty(value = "name", notes = "이름")
        public String name;

        @ApiModelProperty(value = "price", notes = "가격")
        public int price;

        @ApiModelProperty(value = "dealType", notes = "판매방식")
        public String dealType;

        @ApiModelProperty(value = "createdAt", notes = "판매등록시간")
        public LocalDateTime createdAt;
    }



//    @ApiModel("메인페이지 제품정보 Response")
//    @Getter
//    @NoArgsConstructor(access = AccessLevel.PUBLIC)
//    public static class MainInfoRes {
//
//        @ApiModelProperty(value = "아이디", notes = "userId", example = "admin@j2kb.com", required = true)
//        public String name;
//
//        @ApiModelProperty(value = "비밀번호", notes = "password", example = "admin", required = true)
//        public String price;
//
//
//        @Builder
//        public MainAuctionInfoRes(String name, String price) {
//            this.userId = userId;
//            this.password = password;
//            this.name = name;
//        }
//    }

    @ApiModel("메인페이지 제품정보 Response")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class detailInfoRes {

        //사진, 키워드, 이름, 가격, 거래방법, 배송방법, 배송비, 북마크크

    }

    @ApiModel("메인페이지 제품정보 Response")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class detailAutionInfoRes {

        //사진, 키워드, 이름, 가격, 거래방법, 배송방법, 배송비, 북마크크

    }


}
