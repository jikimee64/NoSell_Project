package com.soap.moon.domains.user.repository;

import static com.soap.moon.domains.user.domain.QUserReview.userReview;
import static com.soap.moon.domains.user.domain.QUser.user;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.dto.query.UserReviewDto.MyPageCommon;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
