import React, { useState } from "react";

const HSearch = (props) => {
  const [search, setSearch] = useState("");
  const onChange = (e) => {
    setSearch(e.target.value);
  };

  const onSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <form onSubmit={onSubmit}>
      <button className="search_icon" type="submit" value="submit">
        <i className="fa fa-search"></i>
      </button>
      <input
        type="search"
        name="search"
        placeholder="어떤 상품을 찾고 계세요?"
        id="top_search"
        value={search}
        onChange={onChange}
      />
    </form>
  );
};

export default HSearch;
