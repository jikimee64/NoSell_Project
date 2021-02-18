package com.soap.moon.domains.product.repository;

import com.soap.moon.domains.category.domain.Category;
import com.soap.moon.domains.product.domain.Product;
import com.soap.moon.domains.user.repository.UserRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    //@Query("select p From Product p join fetch p.productImages")
    //Page<Product> findAll(Pageable pageable);
}
