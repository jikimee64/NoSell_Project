package com.soap.moon.domains.product.repository;

import com.soap.moon.domains.product.dto.ProductDto;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    Page<mainProductRes> findMainPageProduct(Pageable pageable, Integer categoryId, String keyword);


}
