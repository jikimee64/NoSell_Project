package com.soap.moon.domains.category.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public class CategoryDto {

//    @ApiModelProperty(value = "id", notes = "id")
//    public Long id;
//
//    @ApiModelProperty(value = "이름", notes = "name")
//    public String name;
//
//    @ApiModelProperty(value = "부모키", notes = "parentId")
//    public Long parentId;
//
//    List<CategoryDto> subCategories;
//
//    @Builder
//    public CategoryDto(Long id, String name, Long parentId) {
//        this.id = id;
//        this.name = name;
//        this.parentId = parentId;
//    }

    @ApiModelProperty(value = "카테고리 대분류", notes = "categoriesOneDepth")
    Map<String, List<CategoryRes>> categoriesOneDepth = new HashMap<>();

    @ApiModelProperty(value = "카테고리 중분류", notes = "categoriesTwoDepth")
    Map<String, List<CategoryRes>> categoriesTwoDepth = new HashMap<>();

    @ApiModel("카테고리 조회 GET")
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class CategoryRes{
        @ApiModelProperty(value = "고유값", notes = "id")
        public Long id;

        @ApiModelProperty(value = "카테고리 이름", notes = "name")
        public String name;
    }

}
