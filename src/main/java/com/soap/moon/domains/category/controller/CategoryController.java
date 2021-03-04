package com.soap.moon.domains.category.controller;

import com.soap.moon.domains.category.dto.CategoryDto.CategoryOfUserRes;
import com.soap.moon.domains.category.service.CategoryService;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.dto.UserDto.SelectOneRes;
import com.soap.moon.domains.user.exception.MemberNotFoundException;
import com.soap.moon.global.common.CommonResponse;
import com.soap.moon.global.config.aop.PerformanceTimeRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"5. Category"}, value = "카테고리")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @ApiOperation(
        httpMethod = "GET", value = "회원 관심 카테고리 조회", notes = "회원에 대한 관심 카테고리(1단계)를 조회한다.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "회원 관심 카테고리 조회 성공", response = CategoryOfUserRes.class)
    })
    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getCategoryOfUser(
        @ApiParam(value = "회원 고유값", required = true, example = "1")
        @PathVariable("userId") Long userId) {

        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(categoryService.getCategoryOfUser(userId))
                .build()
            , HttpStatus.OK);
    }
}
