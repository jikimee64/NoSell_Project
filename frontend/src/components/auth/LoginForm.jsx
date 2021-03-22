import React from "react";
import AuthForm from "./AuthForm";
<<<<<<< HEAD
import {useState, useCallback} from "react";
import {withRouter} from "react-router-dom";

const LoginForm = ({history}) => {
=======
import { useState, useCallback, useContext } from "react";
import { AuthContext } from "../../context/AuthContext";
import { withRouter } from "react-router-dom";
import { authLogin } from "../../api/auth";

const LoginForm = ({ history }) => {
  const { handleLogin } = useContext(AuthContext);
>>>>>>> 2e8e26f9b3f739b8c1815878baa3ee16a931aecf
  const [form, setForm] = useState({
    email: "",
    password: "",
  });

  const onChangeForm = useCallback((e) => {
    const {name, value} = e.target;
    setForm((form) => ({
      ...form,
      [name]: value,
    }));
  }, []);

  const onSubmit = useCallback(
<<<<<<< HEAD
      (e) => {
        e.preventDefault();

        history.push("/");
      },
      [history]
=======
    (e) => {
      e.preventDefault();
      const tryLogin = async () => {
        try {
          const { data } = await authLogin({
            email: form.email,
            password: form.password,
          });
          const token = data.data.accessToken;
          localStorage.setItem("token", token);
          handleLogin({
            nickName: data.data.nickName || "CIA",
            profileImage:
              data.data.profileImage ||
              "https://www.google.com/url?sa=i&url=https%3A%2F%2Fkbbvely.tistory.com%2F16&psig=AOvVaw3wFEkqbfCVvLOHhlPna2Yc&ust=1614665523092000&source=images&cd=vfe&ved=0CAIQjRxqFwoTCPj4uvy3ju8CFQAAAAAdAAAAABAD",
          });
          history.push("/");
        } catch (error) {
          alert("Login Failed..");
          setForm({
            email: "",
            password: "",
          });
        }
      };
      tryLogin();
    },
    [form, history, handleLogin]
>>>>>>> 2e8e26f9b3f739b8c1815878baa3ee16a931aecf
  );

  return <AuthForm type="login" {...{form, onChangeForm, onSubmit}} />;
};

export default withRouter(LoginForm);
