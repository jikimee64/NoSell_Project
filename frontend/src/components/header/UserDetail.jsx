import React from "react";
import { Link } from "react-router-dom";

const UserDetail = ({ handleLogout }) => {
  return (
    <ul>
      <li>
        <Link to="/myPage/review">
          내 별점<i className="star"></i>
        </Link>
      </li>
      <li>
        <Link to="/">kate님의 상점</Link>
      </li>
      <li>
        <a href="/#" onClick={handleLogout}>
          로그아웃
        </a>
      </li>
    </ul>
  );
};

export default React.memo(UserDetail);
