package com.soap.moon.domains.category.controller;

import com.soap.moon.domains.category.dto.CategoryDto;
import com.soap.moon.domains.category.service.CategoryService;
import com.soap.moon.domains.member.dto.UserDto;
import com.soap.moon.global.common.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"1. Category"}, value = "카테고리 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @ApiOperation(
        httpMethod = "GET", value = "카테고리 대분류 조회", notes = "")
    //대분류 카테고리
    @GetMapping
    public ResponseEntity<?> selectCategory() {
        return new ResponseEntity<>(
            CommonResponse.builder()
                .code("200")
                .message("ok")
                .data(
                    categoryService.categoryOneDepth()
                ).build()
            , HttpStatus.OK);
    }

    //중분류 카테고리
    @GetMapping("/{id}")
    public ResponseEntity<?> categoriesTwoDepth(
        @ApiParam(value = "카테고리 ID GET", required = true)
        @PathVariable("id") final Long id) {

            return new ResponseEntity<>(
                CommonResponse.builder()
                    .code("200")
                    .message("ok")
                    .data(
                        categoryService.categoryTwoDepth(id)
                    ).build()
                , HttpStatus.OK);


    }
}