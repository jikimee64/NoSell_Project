package com.soap.moon.domains.product.service;

import com.soap.moon.domains.category.dto.CategoryDto;
import com.soap.moon.domains.product.domain.Product;
import com.soap.moon.domains.product.dto.ProductDto;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import com.soap.moon.domains.product.exception.ProductNotFoundException;
import com.soap.moon.domains.product.repository.ProductRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<mainProductRes> getProductList(Integer page) {

        Page<mainProductRes> products = productRepository.findMainPageProduct(PageRequest.of
            (page, 40, Sort.by("id").descending()), null);
        return getRepositoryInProducts(products);
    }

    public List<mainProductRes> getProductListByCategory(Integer page, Integer categoryId) {

        Page<mainProductRes> products = productRepository.findMainPageProduct(PageRequest.of
            (page, 40, Sort.by("id").descending()), categoryId);
        return getRepositoryInProducts(products);
    }

    private List<mainProductRes> getRepositoryInProducts(Page<mainProductRes> products) {
        if (products.isEmpty()) {
            throw new ProductNotFoundException();
        } else {
            return products.getContent().stream()
                .map(s ->
                    mainProductRes.builder()
                        .id(s.getId())
                        .title(s.getTitle())
                        .price(s.getPrice())
                        .dealType(s.getDealType())
                        .image_url(s.getImage_url())
                        .createdAt(s.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
        }
    }

}
