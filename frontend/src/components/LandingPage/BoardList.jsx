import React, { useState, useEffect } from "react";
import BoardItem from "./BoardItem";
import { getMainList } from "../../api/index.js";

const BoardList = ({ page }) => {
  const [boards, setBoards] = useState({
    isLoading: false,
    data: [],
    error: null,
  });

  const getDataList = async () => {
    try {
      const { data } = await getMainList(page);
      const loadData = data.data;
      setBoards((prev) => ({
        isLoading: false,
        data: [...prev.data, ...loadData],
        error: null,
      }));
    } catch (error) {
      setBoards((prev) => ({
        ...prev,
        isLoading: false,
        error: error,
      }));
    }
  };

  useEffect(() => {
    setBoards((prev) => ({
      ...prev,
      isLoading: true,
      error: null,
    }));
    getDataList();
  }, [page]);

  const { loading, data, error } = boards;

  if (loading) return <h3>Loading~</h3>;
  if (error) return <h3>Error....</h3>;
  if (!data) return <h3>Not Found Data..</h3>;

  return (
    <div className="main_list" id="update_slot">
      <div>
        <ul className="main_row">
          {data.map((board) => (
            <BoardItem key={board.id} {...{ ...board }} />
          ))}
        </ul>
      </div>
    </div>
  );
};

export default BoardList;
