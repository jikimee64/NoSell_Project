package com.soap.moon.domains.user.dto.query;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserReviewDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageCommon {

        private String nickName;
        private LocalDateTime createdAt;
        private long count;
        private Double starsSum;
    }
}
