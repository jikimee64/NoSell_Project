import React from "react";
import { Link } from "react-router-dom";
import HSearch from "./HSearch";

const HeaderA = () => {
  return (
    <div id="header_a">
      <Link to="/">
        <i className="logo"></i>
      </Link>
      <div className="search">
        <HSearch />
      </div>
      <div className="top_gnb">
        <Link to="/signin">
          <i className="signin"></i>로그인
        </Link>
        <Link to="/signup">
          <i className="signup"></i>회원가입
        </Link>
        <Link to="/service">
          <i className="service"></i>고객센터
        </Link>
      </div>
    </div>
  );
};

export default HeaderA;
