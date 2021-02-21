import React, { useState, useCallback } from "react";
import AddtionalBtn from "./AddtionalBtn";
import BoardList from "./BoardList";

const Loading = () => {
  const [page, setPage] = useState(0);

  const nextPage = useCallback(() => {
    setPage((prev) => ++prev);
  }, []);

  return (
    <div className="main_list" id="update_slot">
      <div>
        <BoardList page={page} />
      </div>
      <AddtionalBtn nextPage={nextPage} />
    </div>
  );
};

export default Loading;
