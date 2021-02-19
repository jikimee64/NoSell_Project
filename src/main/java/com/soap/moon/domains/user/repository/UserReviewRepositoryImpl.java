//package com.soap.moon.domains.user.repository;
//
//import static com.soap.moon.domains.user.domain.QUserReview.userReview;
//
//import com.querydsl.core.types.Projections;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import com.soap.moon.domains.user.domain.User;
//import com.soap.moon.domains.user.dto.query.UserReviewDto;
//import com.soap.moon.domains.user.dto.query.UserReviewDto.CountAndSum;
//import java.util.List;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//@RequiredArgsConstructor
//@Repository
//public class UserReviewRepositoryImpl implements UserReviewRepositoryCustom {
//
//    private final JPAQueryFactory queryFactory;
//
//    @Override
//    public List<CountAndSum> findUserReviewCountAndSum(User user) {
//        return queryFactory
//            .select(Projections.constructor(CountAndSum.class,
//                userReview.count(), //타입이 순서에 맞게 생성자에 딱딱 들어가야됨
//                userReview.stars.sum()))
//            .from(userReview)
//            .where(userReview.user.eq(user))
//            .fetch();
//    }
//}
