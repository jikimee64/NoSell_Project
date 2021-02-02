package com.soap.moon.domains.product.repository;

import com.soap.moon.domains.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Category, Long> {

}
