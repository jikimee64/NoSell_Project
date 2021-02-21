import React from "react";
import NavItem from "./NavItem";
import { CATEGORY_LIST } from "../../data";

const NavList = () => {
  return (
    <nav>
      <ul className="sub">
        {CATEGORY_LIST.map((cate, idx) => (
          <NavItem key={cate.name} cate={cate} idx={idx} />
        ))}
      </ul>
    </nav>
  );
};

export default NavList;
