package com.soap.moon.domains.product.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.soap.moon.ControllerTest;
import com.soap.moon.domains.category.dto.CategoryDto.CategoryOfUserRes;
import com.soap.moon.domains.category.dto.CategoryDto.UserLikeCategory;
import com.soap.moon.domains.category.service.CategoryService;
import com.soap.moon.domains.product.domain.DealType;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import com.soap.moon.domains.product.service.ProductService;
import com.soap.moon.domains.user.dto.query.UserReviewDto.MyPageCommon;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

class ProductControllerTest extends ControllerTest {

    private static final String PRODUCT_API_URL = "/api/v1/products";

    @MockBean
    ProductService productService;

    @DisplayName("메인페이지에 출력하는 상품정보를 조회한다.")
    @Test
    void 메인페이지_상품_조회() throws Exception {
        when(productService.getProductList(any())).thenReturn(getStubProducts());
        readByPathVariables(PRODUCT_API_URL + "/{page}/list", 0L,
            jsonPath("$.data.*", hasSize(5)));
    }

    List<mainProductRes> getStubProducts() {
        return List.of(
            mainProductRes.builder().id(1L).title("상품_1").price(10000)
                .dealType(DealType.DIRECT_DEAL).image_url("image_1").createdAt(LocalDateTime.now())
                .build(),
            mainProductRes.builder().id(2L).title("상품_2").price(12000).dealType(DealType.NO_SELL)
                .image_url("image_2").createdAt(LocalDateTime.now()).build(),
            mainProductRes.builder().id(3L).title("상품_3").price(14000).dealType(DealType.ONLINE)
                .image_url("image_3").createdAt(LocalDateTime.now()).build(),
            mainProductRes.builder().id(4L).title("상품_4").price(16000)
                .dealType(DealType.DIRECT_DEAL).image_url("image_4").createdAt(LocalDateTime.now())
                .build(),
            mainProductRes.builder().id(5L).title("상품_5").price(18000).dealType(DealType.NO_SELL)
                .image_url("image_5").createdAt(LocalDateTime.now()).build()
        );
    }
}