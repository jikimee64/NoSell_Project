import React, { useState, useCallback } from "react";
import SearchForm from "./SearchForm";
import { withRouter, Link } from "react-router-dom";
import BtnBox from "./BtnBox";

import "../../asset/css/header.css";

const Header = ({ history }) => {
  const [search, setSearch] = useState("");

  const onChangeSearch = useCallback((e) => {
    setSearch(e.target.value);
  }, []);

  const onSubmitSearch = useCallback(
    (e) => {
      e.preventDefault();
      history.push("/");
    },
    [history]
  );

  return (
    <header>
      <h1 className="logo_bar">
        <Link to="/">
          <i className="logo"></i>
        </Link>
      </h1>
      <SearchForm {...{ search, onChangeSearch, onSubmitSearch }} />
      <BtnBox />
    </header>
  );
};

export default withRouter(Header);
