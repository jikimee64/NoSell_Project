import React from "react";
import "../asset/css/fontawesome/style.css";
import "../asset/css/auth.css";
import AuthTemplate from "../components/auth/AuthTemplate";
import LoginForm from "../components/auth/LoginForm";

const LoginPage = () => {
  return (
      <AuthTemplate>
        <LoginForm/>
      </AuthTemplate>
  );
};

export default LoginPage;
