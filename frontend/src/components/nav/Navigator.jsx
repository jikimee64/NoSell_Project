import React, { useState, useCallback } from "react";
import CategoryList from "./CategoryList";

import "../../asset/css/nav.css";

const Navigator = () => {
  const [isShow, setIsShow] = useState(false);

  const handleShow = useCallback(() => {
    setIsShow((prev) => !prev);
  }, []);

  return (
    <div className="nav">
      <div className="gnb_menu">
        <div className="logo_btn btn">
          <i className="m_home mobile"></i>
        </div>
        <div className="cate_btn btn" onClick={handleShow}>
          <button className="btn_detail">
            카테고리 <i className="fas fa-chevron-down"></i>
          </button>
          <i className="m_category mobile"></i>
          {isShow && <CategoryList />}
        </div>
        <div className="sell_btn btn">
          <i className="m_plus mobile"></i>
          <button className="sell_btn btn_detail">
            <i className="fas fa-camera"></i>판매등록
          </button>
        </div>
        <div className="user_btn btn">
          <i className="m_mymenu mobile"></i>
        </div>
      </div>
    </div>
  );
};

export default Navigator;
