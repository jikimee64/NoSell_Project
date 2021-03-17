import React from "react";

const CateItem = ({ name, idx, handleSelect }) => {
  return (
    <li>
      <a href="/#" onClick={handleSelect} name={name}>
        <i className={`sub_menu${idx + 1}`}></i>
        {name}
      </a>
    </li>
  );
};

export default React.memo(CateItem);
