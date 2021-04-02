//package com.soap.moon.domains.category.service;
//
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.BDDMockito.given;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.soap.moon.domains.category.domain.Category;
//import com.soap.moon.domains.category.dto.CategoryDto;
//import com.soap.moon.domains.category.repository.CategoryRepository;
//import java.util.List;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.util.ReflectionTestUtils;
//
//@ExtendWith(MockitoExtension.class)
//class CategoryServiceTest {
//
//    @Mock
//    private CategoryRepository categoryRepository;
//
//    @Test
//    @DisplayName("카테고리 조회")
//    void categoryOneDepthSelect() {
//
//        given(categoryRepository.findAll()).willReturn(getStubCatogories());
//        CategoryService categoryService = new CategoryService(categoryRepository);
//
//        CategoryDto categoryRoot = categoryService.categoryRoot();
//
//        System.out.println(categoryRoot);
//
//        assertThat(categoryRoot.getSubCategories().size()).isEqualTo(2);
//        assertThat(categoryRoot.getSubCategories().get(0).getSubCategories().size()).isEqualTo(2);
//        assertThat(categoryRoot.getSubCategories().get(1).getSubCategories().size()).isEqualTo(2);
//    }
//
//    /**
//     * ReflectionTestUtils : https://www.baeldung.com/spring-reflection-test-utils
//     */
//    private List<Category> getStubCatogories() {
//        Category sub1 = new Category("SUB1", 0L);
//        Category sub2 = new Category("SUB2", 0L);
//        Category sub11 = new Category("SUB1-1", 1L);
//        Category sub12 = new Category("SUB1-2", 1L);
//        Category sub21 = new Category("SUB2-1", 2L);
//        Category sub22 = new Category("SUB2-2", 2L);
//
//        //ReflectionTestUtils을 이용한 private 필드값 할당 가능 : 기본키 (Builder, Setter 없이)
//        ReflectionTestUtils.setField(sub1, "id", 1L);
//        ReflectionTestUtils.setField(sub2, "id", 2L);
//        ReflectionTestUtils.setField(sub11, "id", 3L);
//        ReflectionTestUtils.setField(sub12, "id", 4L);
//        ReflectionTestUtils.setField(sub21, "id", 5L);
//        ReflectionTestUtils.setField(sub22, "id", 6L);
//
//        List<Category> categories = List.of(
//            sub1, sub2, sub11, sub12, sub21, sub22
//        );
//        return categories;
//    }
//
//}