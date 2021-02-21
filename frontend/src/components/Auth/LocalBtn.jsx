import React from "react";

const LocalBtn = ({ handleLogin }) => {
  return (
    <button type="submit" className="button button_block" onClick={handleLogin}>
      로그인
    </button>
  );
};

export default LocalBtn;
