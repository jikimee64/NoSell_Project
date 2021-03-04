package com.soap.moon.domains.category.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.soap.moon.ControllerTest;
import com.soap.moon.domains.category.dto.CategoryDto.CategoryOfUserRes;
import com.soap.moon.domains.category.dto.CategoryDto.UserLikeCategory;
import com.soap.moon.domains.category.service.CategoryService;
import com.soap.moon.domains.user.dto.query.UserReviewDto.MyPageCommon;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


class CategoryControllerTest extends ControllerTest {

    private static final String CATEGORY_API_URL = "/api/v1/categories";

    @MockBean
    CategoryService categoryService;

    @DisplayName("회원에 대한 관심 카테고리(1단계)를 조회한다.")
    @Test
    void 회원_관심_카테고리_조회() throws Exception{
        MyPageCommon myPage = MyPageCommon.builder().nickName("닉네임").createdAt(LocalDateTime.now()).count(1L).starsSum(2.0).build();
        List<UserLikeCategory> categories = Arrays.asList(UserLikeCategory.builder().categoryId(1L).name("카테고리1").build());
        CategoryOfUserRes build = CategoryOfUserRes.builder().categories(categories)
            .myPageCommon(myPage).build();
        when(categoryService.getCategoryOfUser(any())).thenReturn(build);
        readByPathVariables(CATEGORY_API_URL + "/{userId}", 1L,
            jsonPath("$.data.*", hasSize(2)));
    }

}