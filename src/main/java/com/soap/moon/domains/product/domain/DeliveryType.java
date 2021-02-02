package com.soap.moon.domains.product.domain;

import lombok.Getter;

@Getter
public enum DeliveryType {
    PARCEL("택배"),
    DIRECT_PICKUP("직접수거");

    private String deliveryType;

    DeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }
}
