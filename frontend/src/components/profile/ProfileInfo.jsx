import React from "react";
import "../../asset/css/profileInfo.css";

const ProfileInfo = () => {
  return (
    <section id="my_fixed" className="my_fixed">
      <div className="breadcrum">
        <a href="/#">HOME</a>
        <a href="/#">나의메뉴</a>
      </div>
      <div className="user_info">
        <div>
          <a href="/#" className="info_pic">
            <i className="fas fa-user-circle"></i>
          </a>
          <p className="info_name">
            <span>케이트</span>님의 상점
          </p>
          <a href="./mypage_review.html" className="star_rating">
            <span className="wrap_star">
              <span></span>
            </span>
            <span className="star_count">02</span>
          </a>
          <p>
            <span className="info_month">2021년 01월</span> 시작
          </p>
        </div>
        <p>
          <a href="./mypage_myoption.html" className="info_btn">
            내정보설정
          </a>
          <a href="./category_option.html" className="info_btn">
            관심 카테고리
          </a>
        </p>
      </div>
    </section>
  );
};

export default ProfileInfo;
