import React from "react";
import AuthForm from "./AuthForm";
import { useState, useCallback } from "react";
import { withRouter } from "react-router-dom";

const LoginForm = ({ history }) => {
  const [form, setForm] = useState({
    email: "",
    password: "",
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

      history.push("/");
    },
    [history]
  );

  return <AuthForm type="login" {...{ form, onChangeForm, onSubmit }} />;
};

export default withRouter(LoginForm);
