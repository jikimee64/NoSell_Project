package com.soap.moon.domains.category.service;

import com.soap.moon.domains.category.domain.Category;
import com.soap.moon.domains.category.dto.CategoryDto;
import com.soap.moon.domains.category.dto.CategoryDto.CategoryRes;
import com.soap.moon.domains.category.repository.CategoryRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Map<String, List<CategoryRes>> categoryOneDepth(){
        CategoryDto categoryDto = new CategoryDto();

        List<Category> categories = categoryRepository
            .findCategoryOneDepthByParentIdWhereZero(0L);

        List<CategoryRes> collect = categories.stream()
            .map(ce -> CategoryDto.CategoryRes.builder()
                .id(ce.getId())
                .name(ce.getName()).build())
            .collect(Collectors.toList());

        categoryDto.getCategoriesOneDepth().put("categoryOneDepth", collect);

        return categoryDto.getCategoriesOneDepth();
    }

    public Map<String, List<CategoryRes>> categoryTwoDepth(Long id){
        CategoryDto categoryDto = new CategoryDto();

        List<Category> categories = categoryRepository
            .findCategoryTwoDepthByParentId(id);

        List<CategoryRes> collect = categories.stream()
            .map(ce -> CategoryDto.CategoryRes.builder()
                .id(ce.getId())
                .name(ce.getName()).build())
            .collect(Collectors.toList());

        categoryDto.getCategoriesTwoDepth().put("categoriesTwoDepth", collect);

        return categoryDto.getCategoriesTwoDepth();
    }


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
