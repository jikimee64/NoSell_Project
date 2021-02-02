package com.soap.moon.domains.product.domain;

import lombok.Getter;

@Getter
public enum ProductStatus {
    NEW("새상품"),
    USED("거의새것"),
    OLD("중고");

    private String status;

    ProductStatus(String status){ this.status = status; }

}
