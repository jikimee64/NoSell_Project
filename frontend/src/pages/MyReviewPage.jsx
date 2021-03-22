import React from "react";
import Header from "../components/header/Header";
import Navigator from "../components/nav/Navigator";
import ProfileInfo from "../components/profile/ProfileInfo";
import Review from "../components/profile/Review";

const MyReviewPage = () => {
  return (
    <>
      <Header />
      <Navigator />
      <main>
        <ProfileInfo />
        <Review />
      </main>
    </>
  );
};

export default MyReviewPage;
