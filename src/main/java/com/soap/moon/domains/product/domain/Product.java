package com.soap.moon.domains.product.domain;

import com.mysema.commons.lang.Assert;
import com.soap.moon.domains.category.domain.Category;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.global.common.BaseTimeEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status")
    private ProductStatus productStatus;

    @Column(name = "price")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "deal_type")
    private DealType dealType;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_type")
    private DeliveryType deliveryType;

    @Enumerated(EnumType.STRING)
    @Column(name = "sales_status")
    private SalesStatus salesStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Product(String title, String description, ProductStatus productStatus, int price,
        DealType dealType, DeliveryType deliveryType, SalesStatus salesStatus, User user, Category category){
        Assert.notNull(title, "title must not be null");
        Assert.notNull(description, "description must not be null");
        Assert.notNull(productStatus, "productStatus must not be null");
        Assert.notNull(price, "initPrice must not be null");
        Assert.notNull(dealType, "dealType must not be null");
        Assert.notNull(deliveryType, "deliveryType must not be null");
        Assert.notNull(salesStatus, "salesStatus must not be null");
        Assert.notNull(user, "user must not be null");
        Assert.notNull(category, "category must not be null");

        this.title = title;
        this.description = description;
        this.productStatus = productStatus;
        this.price = price;
        this.dealType = dealType;
        this.deliveryType = deliveryType;
        this.salesStatus = salesStatus;
        this.user = user;
        this.category = category;
    }
}
