import React from "react";

const NavItem = ({ cate, idx }) => {
  return (
    <li>
      <a href="#">
        <i className={`sub_menu${idx + 1}`}></i>
        {cate.name}
      </a>
    </li>
  );
};

export default NavItem;
