import React from "react";

const SlideImage = ({ image }) => {
  return (
    <div className="img_box">
      <img src={image} alt="banner" />
    </div>
  );
};

export default SlideImage;
