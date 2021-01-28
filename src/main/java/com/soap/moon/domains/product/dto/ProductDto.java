package com.soap.moon.domains.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProductDto {

    @ApiModelProperty(value = "이름", notes = "이름")
    public String name;

    @ApiModelProperty(value = "가격", notes = "가격")
    public String price;

    @ApiModelProperty(value = "판매방식", notes = "판매방식")
    public String salesMethod;


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
