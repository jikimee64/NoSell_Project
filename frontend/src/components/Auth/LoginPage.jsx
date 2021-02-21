import React, { useState, useCallback } from "react";
import KakaoLogin from "./KakaoLogin";
import "../../css/signup-login.css";
import EmailInput from "./EmailInput";
import { localLogin } from "../../api";
import PasswordInput from "./PasswordInput";
import LocalBtn from "./LocalBtn";
import Axios from "axios";

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleEmail = useCallback((e) => {
    setEmail(e.target.value);
  }, []);

  const handlePassword = useCallback((e) => {
    setPassword(e.target.value);
  }, []);

  const handleLogin = async () => {
    console.log(JSON.stringify({ email, password }));
    const data = await Axios({
      method: "post",
      url: "/api/v1/login",
      data: JSON.stringify({ email, password }),
      headers: { "Content-Type": "application/json" },
    });
    console.log(data);
  };

  return (
    <article className="form">
      <h2>로그인</h2>
      <EmailInput {...{ email, handleEmail }} />
      <PasswordInput {...{ password, handlePassword }} />
      <p className="field_wrap test">
        <input type="checkbox" />
        <span>I'm not a robot</span>
      </p>
      <LocalBtn handleLogin={handleLogin} />
      <p className="field_wrap find">
        <a href="#" className="find_id">
          아이디 찾기
        </a>
        <a href="#" className="find_pw">
          비밀번호 찾기
        </a>
      </p>
      <p className="field_wrap social">
        <a href="#">
          {" "}
          <img src="/img/naver_login.png" alt="login_icon" /> 네이버 로그인
        </a>
        <a href="#">
          {" "}
          <img src="/img/google_login.png" alt="login_icon" /> 구글 로그인
        </a>
      </p>
      <KakaoLogin />
    </article>
  );
};

export default LoginPage;
