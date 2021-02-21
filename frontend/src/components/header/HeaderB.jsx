import React, { useState } from "react";
import { Link } from "react-router-dom";
import NavList from "./NavList";

const HeaderB = () => {
  const [isDrop, setIsDrop] = useState(false);

  const onDrop = () => {
    setIsDrop(!isDrop);
  };

  return (
    <div className="wrap">
      <div id="header_b">
        <div className="top_btn">
          <button
            className="cate_btn"
            style={!isDrop ? {} : { backgroundColor: "#f3f3f3" }}
            onClick={onDrop}
          >
            카테고리<i className="fas fa-chevron-down"></i>
          </button>
          <Link to="/sell" className="sell_btn">
            <i className="fas fa-camera"></i>판매등록
          </Link>
        </div>
        {isDrop && <NavList />}
      </div>
    </div>
  );
};

export default HeaderB;
