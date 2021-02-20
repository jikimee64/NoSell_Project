package com.soap.moon.domains.product.controller;

import com.soap.moon.domains.category.service.CategoryService;
import com.soap.moon.domains.product.service.ProductService;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.global.config.aop.PerformanceTimeRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"3. product"}, value = "상품")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final CategoryService categoryService;
    private final ProductService productService;

    //메인페이지 상품정보
    @ApiOperation(
        httpMethod = "GET", value = "메인 페이지 상품(전체)", notes = "메인페이지 상품정보")
    @PerformanceTimeRecord
    @GetMapping("/{page}/list")
    public ResponseEntity<?> getProductList(
        @ApiParam(value = "page 0부터", required = true, example = "0")
        @PathVariable(name = "page") @Min(0) Integer page
    ){
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    productService.getProductList(page)
                ).build()
            , HttpStatus.OK);
    }

    //메인페이지 상품정보
    @ApiOperation(
        httpMethod = "GET", value = "서브 페이지 카테고리별 상품", notes = "메인페이지 카테고리 클릭 후 그에맞는 상품정보 반환")
    @PerformanceTimeRecord
    @GetMapping("/{page}/list/categories/{categoryId}")
    public ResponseEntity<?> getProductListByCategory(
        @ApiParam(value = "카테고리 번호(14~53)", required = true, example = "14")
        @PathVariable(name = "categoryId") @Min(14) @Max(53) Integer categoryId,
        @ApiParam(value = "page 0부터", required = true, example = "0")
        @PathVariable(name = "page") @Min(0) Integer page
    ){
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    productService.getProductListByCategory(page, categoryId)
                ).build()
            , HttpStatus.OK);
    }

}
