import React from "react";
import { Link } from "react-router-dom";
import naverLogo from "../../asset/img/naver_login.png";
import googleLogo from "../../asset/img/google_login.png";

const LoginTail = () => {
  return (
    <>
      <div className="field_wrap find">
        <Link to="/" className="find_id">
          아이디 찾기
        </Link>
        <Link to="/" className="find_pw">
          비밀번호 찾기
        </Link>
      </div>
      <div className="field_wrap social">
        <button>
          <img src={naverLogo} alt="login_icon" /> 네이버 로그인
        </button>
        <button>
          <img src={googleLogo} alt="login_icon" /> 구글 로그인
        </button>
      </div>
    </>
  );
};

export default LoginTail;
