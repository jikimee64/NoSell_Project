import React, { useContext, useState, useCallback } from "react";
import { Link } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import Alarm from "./Alarm";
import UserDetail from "./UserDetail";

const BtnBox = () => {
  console.log("Render BtnBox");
  const { userInfo, handleLogout } = useContext(AuthContext);
  const [isAlarmDetail, setIsAlarmDetail] = useState(false);
  const [isUserDetail, setIsUserDetail] = useState(false);
  //profileImage
  const { nickName, isLogin } = userInfo;

  const handleAlarmDetail = useCallback(() => {
    setIsAlarmDetail((prev) => !prev);
  }, []);

  const handleUserDetail = useCallback(() => {
    setIsUserDetail((prev) => !prev);
  }, []);

  const handleUserLogout = useCallback(
    (e) => {
      e.preventDefault();
      handleLogout();
    },
    [handleLogout]
  );

  if (!isLogin) {
    return (
      <ul className="top_gnb">
        <li>
          <Link to="/login">
            <i className="login"></i>로그인
          </Link>
        </li>
        <li>
          <Link to="/register">
            <i className="join"></i>회원가입
          </Link>
        </li>
        <li>
          <Link to="/">
            <i className="service"></i>고객센터
          </Link>
        </li>
      </ul>
    );
  } else {
    return (
      <ul className="top_gnb_after">
        <li className="notify" onClick={handleAlarmDetail}>
          <p className="title">
            <i className="fas fa-bell"></i>
            <span className="count">3</span>
          </p>
          {isAlarmDetail && <Alarm />}
        </li>
        <li className="user" onClick={handleUserDetail}>
          <p className="title">
            <i className="fas fa-user-circle"></i>
            {nickName} 님
          </p>
          {isUserDetail && <UserDetail handleLogout={handleUserLogout} />}
        </li>
      </ul>
    );
  }
};

export default React.memo(BtnBox);
