package com.soap.moon.domains.category.service;

import com.soap.moon.domains.category.domain.CategoryUser;
import com.soap.moon.domains.category.dto.CategoryDto;
import com.soap.moon.domains.category.dto.CategoryDto.UserLikeCategory;
import com.soap.moon.domains.category.dto.CategoryDto.getCategoryOfUserRes;
import com.soap.moon.domains.category.repository.CategoryUserRepository;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.dto.query.UserReviewDto.MyPageCommon;
import com.soap.moon.domains.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryUserRepository categoryUserRepository;
    private final UserRepository userRepository;

    public CategoryDto.getCategoryOfUserRes getCategoryOfUser(Long userId) {

        Map<String, Object> map = new HashMap<>();

        List<CategoryUser> categoryOfUser = categoryUserRepository.findUserCategoryOfUser(userId);

        List<CategoryDto.UserLikeCategory> collect = categoryOfUser.stream()
            .map(s ->
                UserLikeCategory.builder()
                    .categoryId(s.getCategory().getId())
                    .name(s.getCategory().getName())
                    .build()
            )
            .collect(Collectors.toList());

        User user = userRepository.findById(userId).get();

        //마이페이지 왼쪽 유저정보(닉네임,별 평균, 리뷰수, createdAt)
//        List<MyPageCommon> myPageCommons = userRepository
//            .findUserInfoWithReviewCountAndSum(userId);
//
//
//        MyPageCommon myPageUserCommon = MyPageCommon.builder()
//            .nickName(user.getNickName())
//            .stars(myPageCommons.get(0).getSum())
//            .reviewCount(myPageCommons.get(0).getCount())
//            .createdAt(userRepository.findById(userId).get().getCreatedAt())
//            .build();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        MyPageCommon myPageCommon = userRepository
            .findUserInfoWithReviewCountAndSum(user).get(0);
        myPageCommon.setCreatedAt(LocalDateTime
            .parse(myPageCommon.getCreatedAt().format(formatter),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return getCategoryOfUserRes.builder()
            .categories(collect)
            .myPageCommon(myPageCommon)
            .build();

    }

//    @Cacheable(value = "categories")
//    public CategoryDto categoryRoot() {
//        Map<Long, List<CategoryDto>> groupingByParent = categoryRepository.findAll()
//            .stream()
//            .map(ce -> new CategoryDto(ce.getId(), ce.getName(), ce.getParentId()))
//            .collect(Collectors.groupingBy(cd -> cd.getParentId()));
//
//        CategoryDto rootCategoryDto = new CategoryDto(0L, "ROOT", null);
//        addSubCategories(rootCategoryDto, groupingByParent);
//
//        return rootCategoryDto;
//    }
//
//    private void addSubCategories(CategoryDto parent,
//        Map<Long, List<CategoryDto>> groupingByParentId) {
//        //1. parent의 키로 suCategories를 찾는다.
//        List<CategoryDto> subCategories = groupingByParentId.get(parent.getId());
//
//        //종료 조건
//        if (subCategories == null) {
//            return;
//        }
//
//        //2.sub categories 셋팅
//        parent.setSubCategories(subCategories);
//
//        //3.재귀적으로 subcategories들에 대해서도 수행
//        subCategories.stream()
//            .forEach(s -> {
//                addSubCategories(s, groupingByParentId);
//            });
//    }


}
