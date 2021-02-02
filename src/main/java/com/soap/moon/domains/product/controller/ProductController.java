package com.soap.moon.domains.product.controller;

import com.soap.moon.domains.category.domain.Category;
import com.soap.moon.domains.category.repository.CategoryRepository;
import com.soap.moon.domains.category.service.CategoryService;
import com.soap.moon.domains.product.domain.Product;
import com.soap.moon.domains.product.domain.ProductStatus;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import com.soap.moon.domains.product.repository.ProductRepository;
import com.soap.moon.domains.product.service.ProductService;
import com.soap.moon.global.common.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. product"}, value = "상품")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;

    //카테고리 대분류 + 메인
    @ApiOperation(
        httpMethod = "GET", value = "카테고리 + 상품", notes = "메인페이지에 카테고리와 상품정보를 반환")
    @GetMapping("/main")
    public ResponseEntity<?> productAndCategory(){

        Map<String, Object> data = new HashMap<>();

        //카테고리 정보
        data.put("category", categoryService
            .categoryOneDepth());

        //상품정보
        data.put("product", productService.mainProduct());

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    data
                ).build()
            , HttpStatus.OK);
    }

}
