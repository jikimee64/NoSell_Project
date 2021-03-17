import React, { useEffect, useState, useContext } from "react";
import { authCheck } from "../../api/auth";
import { AuthContext } from "../../context/AuthContext";
import { validateToken } from "../tokenUtil";

const AuthHoc = (TargetComp, isLogin) => {
  const AuthCheck = ({ history }) => {
    const [visible, setVisible] = useState(false);
    const { handleLogin } = useContext(AuthContext);

    useEffect(() => {
      const token = localStorage.getItem("token");

      const requestAuth = async () => {
        try {
          const { data } = await authCheck(token);

          const { nickName, profileImage } = data.data;
          handleLogin({ nickName, profileImage });
          if (isLogin || isLogin === "undefined") setVisible(true);
          else history.push("/");
        } catch (error) {
          console.error(error);
          history.push("/");
        }
      };

      if (token && validateToken(token)) {
        requestAuth();
      } else {
        // auth failed...
        // refresh Token Request and Access Token remove
        //localStorage.removeItem("token");
        if (!isLogin) setVisible(true);
        else history.push("/login");
      }
      setVisible(true);
      //return () => setVisible(false);
    }, [history, handleLogin]);

    if (!visible) return null;
    return <TargetComp />;
  };

  return AuthCheck;
};

export default AuthHoc;
