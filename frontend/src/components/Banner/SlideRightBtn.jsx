import React from "react";

const SlideRightBtn = ({ evt }) => {
  return <div className="slide_right" onClick={evt}></div>;
};

export default React.memo(SlideRightBtn);
