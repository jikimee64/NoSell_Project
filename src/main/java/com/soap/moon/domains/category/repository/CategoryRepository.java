package com.soap.moon.domains.category.repository;

import com.soap.moon.domains.category.domain.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    //parentId = 0L
    @Query("select c from Category c where c.parentId = :parentId")
    List<Category> findCategoryOneDepthByParentIdWhereZero(@Param("parentId") Long parentId);

    @Query("select c from Category c where c.parentId = :parentId")

    List<Category> findCategoryTwoDepthByParentId(@Param("parentId") Long parentId);


}
