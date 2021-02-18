package com.soap.moon.domains.product.repository;

import static com.soap.moon.domains.product.domain.QProductImage.productImage;
import static com.soap.moon.domains.product.domain.QProduct.product;
import static org.springframework.util.StringUtils.isEmpty;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soap.moon.domains.product.domain.Product;
import com.soap.moon.domains.product.dto.ProductDto;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//복잡한 조회용 쿼리
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * select * from product left outer join product_image on product.id = product_image.product_id
     * where product_image.id in ( select MIN(id) from product_image group by product_id ) order by
     * product.id desc, product_image.id asc;
     */
    @Override
    public Page<ProductDto.mainProductRes> findMainPageProduct(Pageable pageable, Integer categoryId) {

        QueryResults<mainProductRes> results = queryFactory
            .select(Projections.constructor(mainProductRes.class,
                product.id,
                product.title,
                product.price,
                product.dealType,
                productImage.image_url,
                product.createdAt
            ))
            .from(product)
            .leftJoin(product.productImages, productImage)
            .where(productImage.id.in( //대표이미지 GET
                JPAExpressions
                    .select(productImage.id.min())
                    .from(productImage)
                    .groupBy(productImage.product)
            ), categoryIdEq(categoryId)
            )
            .orderBy(product.id.desc(), productImage.id.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

        List<mainProductRes> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression categoryIdEq(Integer categoryId) {
        return categoryId == null ? null : product.category.id.eq(Long.valueOf(categoryId));
    }


}
