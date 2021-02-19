package com.soap.moon.domains.product.service;

import com.soap.moon.domains.category.dto.CategoryDto;
import com.soap.moon.domains.product.domain.Product;
import com.soap.moon.domains.product.dto.ProductDto;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
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
//    private final ProductImageRepository productImageRepository;
//    private final ProductImageRepositoryImpl productImageRepositoryImpl;

    public List<mainProductRes> getProductList(Integer page) {

        Page<mainProductRes> products = productRepository.findMainPageProduct(PageRequest.of
            (page, 40, Sort.by("id").descending()), null);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<mainProductRes> collect = products.getContent().stream()
            .map(s ->
                mainProductRes.builder()
                    .id(s.getId())
                    .title(s.getTitle())
                    .price(s.getPrice())
                    .dealType(s.getDealType())
                    .image_url(s.getImage_url())
                    .createdAt(
                        LocalDateTime.parse( s.getCreatedAt().format(formatter), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build())
            .collect(Collectors.toList());

        return collect;
    }

    public List<mainProductRes> getProductListByCategory(Integer page, Integer categoryId) {

        log.info("==== page : " + page + "==== categoryId : " + categoryId);

        Page<mainProductRes> products = productRepository.findMainPageProduct(PageRequest.of
            (page, 40, Sort.by("id").descending()), categoryId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<mainProductRes> collect = products.getContent().stream()
            .map(s ->
                mainProductRes.builder()
                    .id(s.getId())
                    .title(s.getTitle())
                    .price(s.getPrice())
                    .dealType(s.getDealType())
                    .image_url(s.getImage_url())
                    .createdAt(
                        LocalDateTime.parse( s.getCreatedAt().format(formatter),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .build())
            .collect(Collectors.toList());

        return collect;
    }

}
