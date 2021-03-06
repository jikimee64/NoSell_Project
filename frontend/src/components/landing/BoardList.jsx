import React, { useState, useEffect, useCallback } from "react";
import BoardMore from "./BoardMore";
import { getMainList, getTargetList } from "../../api/product";
import BoardItem from "./BoardItem";

import "../../asset/css/boardList.css";

const BoardList = ({ categoryId }) => {
  const [page, setPage] = useState(0);
  const [loadData, setLoadData] = useState({
    loading: false,
    data: [],
    error: null,
  });
  console.log(page);
  useEffect(() => {
    setLoadData((prev) => ({
      ...prev,
      loading: true,
    }));
    const requestData = async () => {
      try {
        let res = null;
        if (categoryId) {
          res = await getTargetList(page, categoryId);
        } else {
          res = await getMainList(page);
        }
        console.log(res);
        const getList = res.data.data;
        setLoadData((prev) => ({
          ...prev,
          loading: false,
          data: [...prev.data, ...getList],
        }));
      } catch (error) {
        setLoadData((prev) => ({
          ...prev,
          loading: false,
          error,
        }));
      }
    };
    requestData();
  }, [page, categoryId]);

  const onHandleMore = useCallback(() => {
    setPage((page) => ++page);
  }, []);

  const {loading, data, error} = loadData;

  if (!data.length) {
    if (loading) {
      return <h3>Loading~</h3>;
    }
    return <h3>Not Found Data...</h3>;
  }
  if (error) {
    return <h3>Error...</h3>;
  }

  return (
      <main id="main" className="main">
        <ul className="list">
          {data.map((item) => (
              <BoardItem key={item.id} {...{...item}} />
          ))}
        </ul>
        <BoardMore onHandleMore={onHandleMore}/>
      </main>
  );
};

export default BoardList;
