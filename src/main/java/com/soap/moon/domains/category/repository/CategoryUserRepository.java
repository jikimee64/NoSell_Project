package com.soap.moon.domains.category.repository;

import com.soap.moon.domains.category.domain.CategoryUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryUserRepository extends JpaRepository<CategoryUser, Long> {

    @Query("select uc from CategoryUser uc where uc.user.id = :userId")
    List<CategoryUser> findUserCategoryOfUser(@Param("userId") Long userId);
}
