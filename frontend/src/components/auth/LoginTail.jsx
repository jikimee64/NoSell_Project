<<<<<<< HEAD
import React from "react";
import {Link} from "react-router-dom";
=======
import React, { useContext } from "react";
import { Link } from "react-router-dom";
>>>>>>> 2e8e26f9b3f739b8c1815878baa3ee16a931aecf
import naverLogo from "../../asset/img/naver_login.png";
import { GoogleLogin } from "react-google-login";
import { loginGoogle } from "../../api/auth";
import { AuthContext } from "../../context/AuthContext";
import { withRouter } from "react-router-dom";

const LoginTail = ({ history }) => {
  const { handleLogin } = useContext(AuthContext);
  const responseGoogle = async (res) => {
    try {
      const { data } = await loginGoogle(res.tokenId);
      const { nickName, profileImage, accessToken } = data.data;
      localStorage.setItem("token", accessToken);
      handleLogin(nickName, profileImage);
      history.push("/");
    } catch (err) {
      console.error(err);
    }
  };

  return (
<<<<<<< HEAD
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
            <img src={naverLogo} alt="login_icon"/> 네이버 로그인
          </button>
          <button>
            <img src={googleLogo} alt="login_icon"/> 구글 로그인
          </button>
        </div>
      </>
=======
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
        <button className="social_naver">
          <img src={naverLogo} alt="login_icon" /> 네이버 로그인
        </button>
        <GoogleLogin
          className="social_google"
          clientId="935038106607-3thfafv49tnhj5reg8c4qbsq15jqnhs8.apps.googleusercontent.com"
          buttonText="Google Login"
          onSuccess={responseGoogle}
          onFailure={responseGoogle}
          cookiePolicy={"single_host_origin"}
        />
      </div>
    </>
>>>>>>> 2e8e26f9b3f739b8c1815878baa3ee16a931aecf
  );
};

export default withRouter(LoginTail);
