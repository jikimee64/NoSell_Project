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
<<<<<<< HEAD
      <>
        <h1>Main Page</h1>
        <BoardList/>
        <Footer/>
      </>
=======
    <>
      <Header />
      <Navigator />
      <BoardList categoryId={id} />
      <Footer />
    </>
>>>>>>> 2e8e26f9b3f739b8c1815878baa3ee16a931aecf
  );
};

export default MainPage;
