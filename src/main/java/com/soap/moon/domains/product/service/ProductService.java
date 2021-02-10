package com.soap.moon.domains.product.service;

import com.soap.moon.domains.category.dto.CategoryDto;
import com.soap.moon.domains.product.domain.Product;
import com.soap.moon.domains.product.dto.ProductDto;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import com.soap.moon.domains.product.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<mainProductRes> mainProduct(Integer page) {

        //List<Product> product = productRepository.findAll(PageRequest.of(page, 40));

        Page<Product> products = productRepository.findAll(PageRequest.of
            (page, 40, Sort.by("id").descending()));

        List<mainProductRes> collect = products.getContent().stream()
            .map(s ->
                mainProductRes.builder()
                    .id(s.getId())
                    .name(s.getTitle())
                    .price(s.getPrice())
                    .dealType(s.getDealType().getDealType())
                    .createdAt(s.getCreatedAt())
                    .build())
            .collect(Collectors.toList());

        return collect;
    }

}
