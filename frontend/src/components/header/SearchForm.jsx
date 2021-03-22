import React from "react";

const SearchForm = ({ search, onChangeSearch, onSubmitSearch }) => {
  return (
    <form className="search_bar" onSubmit={onSubmitSearch}>
      <button className="search_btn" type="submit" value="submit">
        <i className="fas fa-search"></i>
      </button>
      <input
        type="text"
        name="search"
        placeholder=" 어떤 상품을 찾고 계세요?"
        className="search_input"
        value={search}
        onChange={onChangeSearch}
      />
    </form>
  );
};

export default SearchForm;
