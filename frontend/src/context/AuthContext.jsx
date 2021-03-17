import React, { createContext, useState, useCallback, useEffect } from "react";
import { withRouter } from "react-router-dom";
import { authCheck } from "../api/auth";
import { validateToken } from "../utils/tokenUtil";

export const AuthContext = createContext();

const Container = ({ children, history }) => {
  const [userInfo, setUserInfo] = useState({
    nickName: "",
    profileImage: "",
    isLogin: false,
  });

  useEffect(() => {
    const initAuth = async () => {
      const token = localStorage.getItem("token");
      if (token && validateToken(token)) {
        const { data } = await authCheck(token);

        const { nickName, profileImage } = data.data;
        setUserInfo({
          nickName,
          profileImage,
          isLogin: true,
        });
      }
    };
    initAuth();
  }, []);

  const handleLogin = useCallback((newUser) => {
    const { nickName, profileImage } = newUser;
    setUserInfo({
      nickName,
      profileImage,
      isLogin: true,
    });
  }, []);

  const handleLogout = useCallback(() => {
    localStorage.removeItem("token");
    setUserInfo((prev) => ({
      ...prev,
      isLogin: false,
    }));
    history.push("/");
  }, [history]);

  return (
    <AuthContext.Provider value={{ userInfo, handleLogin, handleLogout }}>
      {children}
    </AuthContext.Provider>
  );
};

export default withRouter(Container);
