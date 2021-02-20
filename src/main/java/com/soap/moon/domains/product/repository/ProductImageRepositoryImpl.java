//package com.soap.moon.domains.product.repository;
//
//import static com.soap.moon.domains.product.domain.QProductImage.productImage;
//import static com.soap.moon.domains.product.domain.QProduct.product;
//
//import com.querydsl.core.QueryResults;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.soap.moon.domains.product.domain.Product;
//import com.soap.moon.domains.product.domain.ProductImage;
//import java.util.List;
//import javax.persistence.EntityManager;
//
//public class ProductImageRepositoryImpl {
//
//    private final JPAQueryFactory queryFactory;
//
//    public ProductImageRepositoryImpl(EntityManager em) {
//        this.queryFactory = new JPAQueryFactory(em);
//    }
//
//    public String findFirstByOrderByIdDesc(Product product)  {
//        List<ProductImage> productImages = queryFactory
//            .selectFrom(productImage)
//            .orderBy(productImage.id.asc())
////            .offset(1) //0부터 시작(zero index)
//            .where(productImage.product.eq(productImage.product))
//            .limit(1) //최대 2건 조회
//            .fetch();
//
//        return productImages.get(0).getImage_url();
//    }
//}
