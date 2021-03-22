import React from "react";
import { Link } from "react-router-dom";

const SubItem = ({ name, path }) => {
  return (
    <li>
      <Link to={`/products/categories/14`}>{name}</Link>
    </li>
  );
};

export default SubItem;
