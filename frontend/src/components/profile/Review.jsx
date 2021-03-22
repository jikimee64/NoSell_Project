import React from "react";
import "../../asset/css/review.css";
import Test from "../../asset/img/logo.svg";

const Review = () => {
  return (
    <section className="mypage_con" id="mypage_con">
      <article className="reviews">
        <div>
          <div className="review_title">
            <h2>4.0</h2>
            <p className="star_rating">
              <span className="wrap_star">
                <span></span>
              </span>
              <span className="star_count">02</span>
            </p>
          </div>
          <div className="review_score">
            <p>
              칭찬받았어요<i className="smile"></i>
              <span>01</span>
            </p>
            <p>
              노력해볼게요<i className="sad"></i>
              <span>00</span>
            </p>
          </div>
        </div>
        <ul className="review_text">
          <li>
            <div className="text_name">
              <img src={Test} alt="Img" />
              <h3>
                슈퍼<span className="text_day">2021.03.01</span>
              </h3>
            </div>
            <p className="review">
              감사합니다 잘쓸게요! 너무 맘에 들어요! 친절하고 굳굳굳! 상품설명이
              자세하고, 응답도 빠르고 시간약속을 잘 지켜주시다니! 무엇보다
              상품상태가 설명한것과 같고, 친절하고 매너가 좋았어요~ 좋은 상품을
              저렴하게 판매해주셔서 감사합니다.
            </p>
          </li>
        </ul>
        <button className="more">
          더보기<i className="fas fa-chevron-down"></i>
        </button>
      </article>
    </section>
  );
};

export default Review;
