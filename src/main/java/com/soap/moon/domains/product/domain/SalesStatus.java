package com.soap.moon.domains.product.domain;

import lombok.Getter;

@Getter
public enum SalesStatus {

    SALE("판매중"),
    COMPLETED("판매완료");

    private String salesStatus;

    SalesStatus(String salesStatus) {
        this.salesStatus = salesStatus;
    }
}
