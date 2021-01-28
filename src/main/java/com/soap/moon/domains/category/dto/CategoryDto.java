package com.soap.moon.domains.category.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("카테고리 정보 Response")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class CategoryDto {

    @ApiModelProperty(value = "카테고리", notes = "카테고리 정보")
    private List<List<String>> category;

    @Builder
    public CategoryDto(List<List<String>> category) {
        this.category = new ArrayList<>();
    }

}
