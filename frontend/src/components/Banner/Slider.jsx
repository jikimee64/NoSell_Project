import React, { useState, useCallback, useRef, useEffect } from "react";
import SlideImage from "./SlideImage";
import SlideLeftBtn from "./SlideLeftBtn";
import SlideRightBtn from "./SlideRightBtn";

const bannerList = [
  "http://localhost:3000/img/banner1.svg",
  "http://localhost:3000/img/banner2.svg",
  "http://localhost:3000/img/banner3.svg",
];

const Slider = () => {
  const [currentIdx, setCurrentIdx] = useState(0);

  const goToPrevSlide = useCallback(() => {
    setCurrentIdx((prev) => {
      return prev === 0 ? bannerList.length - 1 : prev - 1;
    });
  }, []);

  const goToNextSlide = useCallback(() => {
    setCurrentIdx((prev) => {
      return prev === 2 ? 0 : prev + 1;
    });
  }, []);

  useEffect(() => {
    const id = setInterval(() => {
      goToNextSlide();
    }, 10000);
    return () => {
      clearInterval(id);
    };
  }, []);

  return (
    <section id="slider">
      <SlideImage image={bannerList[currentIdx]} />
      <SlideLeftBtn evt={goToPrevSlide} />
      <SlideRightBtn evt={goToNextSlide} />
    </section>
  );
};

export default Slider;
