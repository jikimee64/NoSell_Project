import React, { useState, useCallback } from "react";
import CateItem from "./CateItem";
import SubItem from "./SubItem";

const cate_list = {
  패션의류: ["여성의류", "남성의류"],
  패션잡화: ["신발/가방", "패션잡화", "쥬얼리", "명품"],
  유아용품: ["출산/육아", "장난감/완구", "유아동의류/잡화", "유아동생활용품"],
  "가구/생활": ["생활용품", "가구/소품DIY", "주방/욕실"],
  "취미/컬렉션": ["골동품/희귀품", "RC카/드론", "피규어/키덜트", "게임/악기"],
  "디지털/가전": [
    "핸드폰/태블릿",
    "노트북/PC/주변기기",
    "카메라/음향기기",
    "생활가전",
  ],
  "스포츠/레저": ["여행/숙박", "스포츠용품", "레저용품"],
  "뷰티/미용": ["화장품/향수", "바디/헤어"],
  반려동식물: ["동물용품", "식물용품"],
  "자동차/공구": ["자동차용품", "바이크용품", "공구/안전/산업용품"],
  "도서/굿즈": ["도서/문구", "스타굿즈/티켓", "CD/DVD", "미술용품/상품권"],
  기타: ["부동산", "건강/의료", "재능/서비스"],
  무료나눔: ["생활용품", "식품/기타"],
};

const CategoryList = () => {
  const [selected, setSelected] = useState("패션의류");

  const handleSelect = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();
    setSelected(e.currentTarget.name);
  }, []);

  return (
    <nav className="gnb">
      <ul className="sub">
        {Object.keys(cate_list).map((name, idx) => (
          <CateItem key={name} {...{ name, idx }} handleSelect={handleSelect} />
        ))}
      </ul>
      <ul className="sub_depth">
        {cate_list[selected].map((ele) => (
          <SubItem key={ele} name={ele} />
        ))}
      </ul>
    </nav>
  );
};

export default CategoryList;
