package com.soap.moon.domains.user.repository;

import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.dto.query.UserReviewDto.MyPageCommon;
import java.util.List;

//QueryDsl 전용
public interface UserRepositoryCustom {
    List<MyPageCommon> findUserInfoWithReviewCountAndSum(User userParam);

}

