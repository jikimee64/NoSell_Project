package com.soap.moon.domains.category.dto;

import com.soap.moon.domains.user.dto.query.UserReviewDto.MyPageCommon;
import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


//@ApiModel("카테고리 조회 GET")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CategoryDto {

//    private Long id;
//    private String name;
//    private Long parentId;
//    private List<CategoryDto> subCategories;
//
//    @Builder
//    public CategoryDto(Long id, String name, Long parentId){
//        this.id = id;
//        this.name = name;
//        this.parentId = parentId;
//    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    @Builder
    public static class UserLikeCategory{
        private Long categoryId;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    @Builder
    public static class CategoryOfUserRes{
        private List<UserLikeCategory> categories;
        private MyPageCommon myPageCommon;
    }

}
