package com.soap.moon.domains.product.repository;

import com.soap.moon.domains.category.domain.Category;
import com.soap.moon.domains.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
