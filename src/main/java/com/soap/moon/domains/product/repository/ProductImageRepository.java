//package com.soap.moon.domains.product.repository;
//
//import com.soap.moon.domains.product.domain.Product;
//import com.soap.moon.domains.product.domain.ProductImage;
//import com.soap.moon.domains.user.domain.User;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
//
//    @Query("select pi.image_url from ProductImage pi where pi.product = :product")
//    String findFirstByOrderByIdDesc(@Param("product") Product product);
//
//}
