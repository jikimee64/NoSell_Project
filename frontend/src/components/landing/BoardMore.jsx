import React from "react";

const BoardMore = ({onHandleMore}) => {
  return (
      <div className="more_load">
        <button onClick={onHandleMore}>
          더보기 <i className="fas fa-chevron-down"></i>
        </button>
      </div>
  );
};

export default BoardMore;
