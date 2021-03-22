import React from "react";
import {Link} from "react-router-dom";
import logoImg from "../../asset/img/logo.svg";

const AuthTemplate = ({children}) => {
  return (
<<<<<<< HEAD
      <section className="form">
        <h1 className="form_logo">
          <Link to="/">
            <img src={logoImg} alt="logo"/>
          </Link>
        </h1>
        <article className="tab_cont">{children}</article>
        <ul class="tab_group">
          <li class="tab active">
          <span>
            처음이신가요?<Link to="/register">회원가입</Link>
          </span>
          </li>
          <li class="tab">
            <Link to="/login">로그인</Link>
          </li>
        </ul>
      </section>
=======
    <section className="form">
      <h1 className="form_logo">
        <Link to="/">
          <img src={logoImg} alt="logo" />
        </Link>
      </h1>
      <article className="tab_cont">{children}</article>
      <ul className="tab_group">
        <li className="tab active">
          <span>
            처음이신가요?<Link to="/register">회원가입</Link>
          </span>
        </li>
        <li className="tab">
          <Link to="/login">로그인</Link>
        </li>
      </ul>
    </section>
>>>>>>> 2e8e26f9b3f739b8c1815878baa3ee16a931aecf
  );
};

export default AuthTemplate;
