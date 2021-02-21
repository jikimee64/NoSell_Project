import React from "react";

const AddtionalBtn = ({ nextPage }) => {
  return (
    <button className="more_load" onClick={nextPage}>
      더보기<i className="fas fa-chevron-down"></i>
    </button>
  );
};

export default React.memo(AddtionalBtn);
