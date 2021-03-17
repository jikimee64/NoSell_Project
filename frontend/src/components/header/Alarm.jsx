import React from "react";

const Alarm = () => {
  return (
    <ul>
      <li>
        <i className="fas fa-bell"></i>알림소식
      </li>
      <li>
        게시글에 댓글 +1 <span>15분 전</span>
      </li>
      <li>
        게시글에 댓글 +1 <span>15분 전</span>
      </li>
      <li>
        경매에 낙찰 됐습니다.<span>하루 전</span>
      </li>
      <li>
        모두보기<i className="fas fa-chevron-right"></i>
      </li>
    </ul>
  );
};

export default Alarm;
