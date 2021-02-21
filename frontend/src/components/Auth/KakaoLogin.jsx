import React from "react";
import kakaoImg from "../../asset/img/kakao_login_medium_narrow.png";

const { Kakao } = window;

const KakaoLogin = () => {
  const loginWithKakao = () => {
    console.log("Access");
    Kakao.Auth.login({
      success: (authObj) => {
        console.log(authObj);
      },
    });
  };

  return (
    <button onClick={loginWithKakao}>
      <img src={kakaoImg} />
    </button>
  );
};

export default KakaoLogin;
