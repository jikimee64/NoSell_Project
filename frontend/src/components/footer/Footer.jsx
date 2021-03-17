import React from "react";
import { Link } from "react-router-dom";

import "../../asset/css/footer.css";

const Footer = () => {
  return (
    <footer id="footer" className="footer">
      <article className="f_wrap">
        <div>
          <ul className="footer_top">
            <li>
              <Link to="/test">Anpalmarket Inc.</Link>
            </li>
            <li>
              <Link to="/">FAQ</Link>
            </li>
            <li>
              <Link to="/">이용약관</Link>
            </li>
            <li>
              <Link to="/">개인정보처리방침</Link>
            </li>
          </ul>
          <address className="footer_bottom">
            <p>
              (주)안팔마켓 대표이사 : 안팔아 |서울특별시 중구 여기살아 7-502 1층
              사업자등록번호 : 155-85-28495통신판매업신고번호 :
              제2356-서울중구-1022호 사업자정보확인 고객 문의 : 02-334-4394(평일
              10시~16시) | 팩스 : 02-3231-4234{" "}
              <em>
                (주)안팔마켓은 통신판매중개자로서 거래당사자가 아니며, 판매자가
                등록한 상품정보 및 거래에 대해 (주)안팔마켓은 일체 책임을 지지
                않습니다.
              </em>
            </p>
            <p> &copy; Copyright Anpalmarket ALL RIGHT RESERVED</p>
          </address>
        </div>
      </article>
    </footer>
  );
};

export default Footer;
