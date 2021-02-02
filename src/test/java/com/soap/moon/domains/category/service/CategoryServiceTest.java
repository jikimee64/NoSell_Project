package com.soap.moon.domains.category.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.soap.moon.domains.category.dto.CategoryDto.CategoryRes;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.config.location=" +
    "classpath:/application-dev.yml" +
    ",classpath:/application-secret.yml")
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    @DisplayName("카테고리 대분류 조회")
    void categoryOneDepthSelect(){
        int expectedSize = 13;

        Map<String, List<CategoryRes>> map = categoryService.categoryOneDepth();

        assertThat(map.get("categoryOneDepth").size()).isEqualTo(expectedSize);
    }

    @Test
    @DisplayName("카테고리 중분류 조회")
    void categoryTwoDepthSelect(){
        Long id = 2L;
        int expectedSize = 4;

        Map<String, List<CategoryRes>> map = categoryService.categoryTwoDepth(id);

        assertThat(map.get("categoriesTwoDepth").size()).isEqualTo(expectedSize);
    }

}