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
import com.soap.moon.global.config.aop.PerformanceTimeRecord;
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

@Api(tags = {"4. product"}, value = "상품")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;

    //메인페이지 상품정보
    @ApiOperation(
        httpMethod = "GET", value = "상품", notes = "메인페이지 상품정보")
    @PerformanceTimeRecord
    @GetMapping
    public ResponseEntity<?> productAndCategory(){
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    productService.mainProduct()
                ).build()
            , HttpStatus.OK);
    }

}
