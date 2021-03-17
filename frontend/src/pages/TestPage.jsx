import React, { useContext } from "react";
import { AuthContext } from "../context/AuthContext";

const MyPage = () => {
  const { handleLogout } = useContext(AuthContext);

  return (
    <div>
      MyPage<button onClick={() => handleLogout()}>Logout!</button>
    </div>
  );
};

export default MyPage;
