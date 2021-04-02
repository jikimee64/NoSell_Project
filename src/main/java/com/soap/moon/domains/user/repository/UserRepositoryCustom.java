package com.soap.moon.domains.user.repository;

import com.soap.moon.domains.product.dto.ProductDto.mainProductRes;
import com.soap.moon.domains.user.domain.User;
import com.soap.moon.domains.user.dto.query.UserReviewDto.MyPageCommon;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

//QueryDsl 전용
public interface UserRepositoryCustom {

    List<MyPageCommon> findUserInfoWithReviewCountAndSum(User userParam);

    Page<mainProductRes> findSalesProductsByUser(Pageable pageable, Long memberId);

}

