package com.soap.moon.domains.category.dto;

import com.soap.moon.domains.category.domain.Category;
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


@ApiModel("카테고리 조회 GET")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class CategoryDto {

    private Long id;
    private String name;
    private Long parentId;
    private List<CategoryDto> subCategories;

    @Builder
    public CategoryDto(Long id, String name, Long parentId){
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

}
