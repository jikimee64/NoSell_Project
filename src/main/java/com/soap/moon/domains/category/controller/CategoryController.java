//package com.soap.moon.domains.category.controller;
//
//import com.soap.moon.domains.category.service.CategoryService;
//import com.soap.moon.global.common.CommonResponse;
//import com.soap.moon.global.config.aop.PerformanceTimeRecord;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
////@Api(tags = {"1. Category"}, value = "카테고리 조회")
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/categories")
//public class CategoryController {
//
//    private final CategoryService categoryService;
//
////    @ApiOperation(
////        httpMethod = "GET", value = "카테고리", notes = "메인페이지 카테고리정보")
//    @PerformanceTimeRecord
//    @GetMapping
//    public ResponseEntity<?> categories() {
//        return new ResponseEntity<>(
//            CommonResponse.builder()
//                .code("200")
//                .message("ok")
//                .data(
//                    categoryService.categoryRoot()
//                ).build()
//            , HttpStatus.OK);
//    }
//
//    //카테고리 하나...
////    @ApiOperation(
////        httpMethod = "GET", value = "중분류 카테고리", notes = "중분류 카테고리 Response")
////    @PerformanceTimeRecord
////    @GetMapping("/{id}")
////    public ResponseEntity<?> categoriesTwoDepth(
////        @ApiParam(value = "1", required = true)
////        @PathVariable("id") final Long id) {
////
////            return new ResponseEntity<>(
////                CommonResponse.builder()
////                    .code("200")
////                    .message("ok")
////                    .data(
////                        categoryService.categoryTwoDepth(id)
////                    ).build()
////                , HttpStatus.OK);
////    }
//}
