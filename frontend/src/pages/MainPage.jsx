import React from "react";
import Footer from "../components/footer/Footer";
import BoardList from "../components/landing/BoardList";
import "../asset/css/media.css";

const MainPage = () => {
  return (
    <>
      <h1>Main Page</h1>
      <BoardList />
      <Footer />
    </>
  );
};

export default MainPage;
