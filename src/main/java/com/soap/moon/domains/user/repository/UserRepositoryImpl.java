package com.soap.moon.domains.user.repository;

import static com.soap.moon.domains.user.domain.QUser.user;
import static com.soap.moon.domains.product.domain.QProduct.product;
import static com.soap.moon.domains.product.domain.QProductImage.productImage;
import static com.soap.moon.domains.user.domain.QUserReview.userReview;
import static org.springframework.util.StringUtils.isEmpty;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.dto.query.UserReviewDto.MyPageCommon;
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
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * SELECT nickname, user.created_at, count(*), sum(user_review.stars) FROM user left outer join
     * user_review on user.id = user_id where user_id = 1
     */
    @Override
    public List<MyPageCommon> findUserInfoWithReviewCountAndSum(User userParam) {
        return queryFactory
            .select(Projections.constructor(MyPageCommon.class,
                user.nickName,
                user.createdAt,
                user.count(),
                userReview.stars.avg()
            ))
            .from(user)
            .leftJoin(user.userReviews, userReview)
            .where(userReview.user.id.eq(userParam.getId()))
            .fetch();
    }

    @Override
    public Page<mainProductRes> findSalesProductsByUser(Pageable pageable, Long memberId) {
        QueryResults<mainProductRes> results = queryFactory
                .select(Projections.constructor(mainProductRes.class,
                    product.id,
                    product.title,
                    product.price,
                    product.dealType,
                    product.salesStatus,
                    productImage.image_url,
                    product.createdAt
                ))
                .from(product)
                .leftJoin(product.productImages, productImage)
                .where(
                    productImage.id.in( //대표이미지 GET
                        JPAExpressions
                            .select(productImage.id.min())
                            .from(productImage)
                            .groupBy(productImage.product)
                    ), memberIdEq(memberId)
                )
                .orderBy(product.id.desc(), productImage.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

            List<mainProductRes> content = results.getResults();
            long total = results.getTotal();

            return new PageImpl<>(content, pageable, total);
        }

        private BooleanExpression memberIdEq(Long memberId) {
            return memberId == null ? null : product.user.id.eq(memberId);
        }

    //    @Override
//    public MemberAuthorityDto findOneWithAuthoritiesByAccount(Account account) {
//        return queryFactory
//            .select(
//                new QMemberAuthorityDto(
//                    member.account,
//                    member.password,
//                    member.name,
//                    member.status,
//                    memberAuthority.authority.authorityName
//                    ))
//            .from(member)
//            .leftJoin(member.authorities, memberAuthority)
//            .where(member.account.eq(account))
//            .fetchOne();
//    }


}


