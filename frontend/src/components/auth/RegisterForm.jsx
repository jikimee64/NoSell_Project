import React from "react";
import AuthForm from "./AuthForm";
import { useState, useCallback } from "react";
import { withRouter } from "react-router-dom";

const RegisterForm = ({ history }) => {
  const [form, setForm] = useState({
    email: "",
    password: "",
    passwordConfirm: "",
    phone: "",
    nickName: "",
  });

  const onChangeForm = useCallback((e) => {
    const { name, value } = e.target;
    setForm((form) => ({
      ...form,
      [name]: value,
    }));
  }, []);

  const onSubmit = useCallback(
    (e) => {
      e.preventDefault();

      history.push("/login");
    },
    [history]
  );

  return <AuthForm type="register" {...{ form, onChangeForm, onSubmit }} />;
};

export default withRouter(RegisterForm);
