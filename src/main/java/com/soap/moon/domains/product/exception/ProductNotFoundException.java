package com.soap.moon.domains.product.exception;

import com.soap.moon.global.error.ErrorCode;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND.getMessage());
    }

    public ProductNotFoundException(Exception ex) {
        super(ex);
    }
}

