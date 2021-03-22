package com.soap.moon.domains.product.controller;

import com.soap.moon.domains.category.service.CategoryService;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import com.soap.moon.domains.product.service.ProductService;
import com.soap.moon.domains.user.dto.UserDto.SelectOneRes;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.global.config.aop.PerformanceTimeRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = {"3. product"}, value = "상품")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    //메인페이지 상품정보
    @ApiOperation(
        httpMethod = "GET", value = "메인 페이지 상품(전체)", notes = "메인페이지 상품정보")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "메인 페이지 상품 전체 조회 성공", response = mainProductRes.class)
    })
    //@PerformanceTimeRecord
    @GetMapping("/{page}/list")
    public ResponseEntity<?> getProductList(
        @ApiParam(value = "page 0부터", required = true, example = "0")
        @PathVariable(name = "page") @Min(0) Integer page
    ) {
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    productService.getProductList(page)
                ).build()
            , HttpStatus.OK);
    }

    //메인페이지 카테고리별 상품정보
    @ApiOperation(
        httpMethod = "GET", value = "서브 페이지 카테고리별 상품", notes = "메인페이지 카테고리 클릭 후 그에맞는 상품정보 반환")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "서브 페이지 카테고리별 상품 조회 성공", response = mainProductRes.class)
    })
    //@PerformanceTimeRecord
    @GetMapping("/{page}/list/categories/{categoryId}")
    public ResponseEntity<?> getProductListByCategory(
        @ApiParam(value = "카테고리 번호(14~53)", required = true, example = "14")
        @PathVariable(name = "categoryId") @Min(14) @Max(53) Integer categoryId,
        @ApiParam(value = "page 0부터", required = true, example = "0")
        @PathVariable(name = "page") @Min(0) Integer page
    ) {
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    productService.getProductListByCategory(page, categoryId)
                ).build()
            , HttpStatus.OK);
    }

    //메인 페이지 검색
    @ApiOperation(
        httpMethod = "GET", value = "메인 페이지 상품(검색)", notes = "검색 후메인페이지 상품정보")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "메인 페이지 상품 전체 조회 성공", response = mainProductRes.class)
    })
    //@PerformanceTimeRecord
    @GetMapping("/{page}/search")
    public ResponseEntity<?> searchProductList(
        @ApiParam(value = "page 0부터", required = true, example = "0")
        @PathVariable(name = "page") @Min(0) Integer page,
        @ApiParam(value = "검색 키워드", required = true, example = "여성의류")
        @RequestParam(name = "q") String keyword
    ) {
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    productService.searchProductList(page, keyword)
                ).build()
            , HttpStatus.OK);
    }

}
