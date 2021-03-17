import React from "react";
import Footer from "../components/footer/Footer";
import BoardList from "../components/landing/BoardList";
import Header from "../components/header/Header";
import Navigator from "../components/nav/Navigator";
import { useParams } from "react-router";

const MainPage = () => {
  const { id } = useParams();
  // undefined or number 14 ~ 53

  return (
    <>
      <Header />
      <Navigator />
      <BoardList categoryId={id} />
      <Footer />
    </>
  );
};

export default MainPage;
