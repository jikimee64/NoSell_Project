package com.soap.moon.domains.product.domain;

import lombok.Getter;

@Getter
public enum DealType {
    DIRECT_DEAL("직거래"),
    ONLINE("온라인 거래"),
    NO_SELL("안팔경매");

    private String dealType;

    DealType(String dealType) {
        this.dealType = dealType;
    }
}
