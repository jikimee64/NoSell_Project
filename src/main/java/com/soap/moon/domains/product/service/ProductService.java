package com.soap.moon.domains.product.service;

import com.soap.moon.domains.category.dto.CategoryDto;
import com.soap.moon.domains.product.domain.Product;
import com.soap.moon.domains.product.dto.ProductDto;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import com.soap.moon.domains.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<mainProductRes> mainProduct() {

        List<Product> product = productRepository.findAll();

        List<mainProductRes> collect = product.stream()
            .map(s ->
                mainProductRes.builder()
                    .name(s.getTitle())
                    .price(s.getPrice())
                    .dealType(s.getDealType().getDealType())
                    .createdAt(s.getCreatedAt())
                    .build())
            .collect(Collectors.toList());

        return collect;
    }

}
