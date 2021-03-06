import React from "react";
import delImg from "../../asset/img/del_tag.svg";
import aucImg from "../../asset/img/auc_tag.svg";

function settingPrice(price) {
  if (!(price instanceof Number)) {
    price = parseInt(price);
  }
  price += "";
  return price.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

<<<<<<< HEAD
const BoardItem = ({title, price, dealType, image_url, createdAt}) => {
  //console.log(props);
  console.log(createdAt);
=======
const BoardItem = ({ title, price, dealType, image_url, createdAt }) => {
>>>>>>> 2e8e26f9b3f739b8c1815878baa3ee16a931aecf
  return (
      <li className="item">
        <div className="item_img">
          <img src={image_url} alt="product_img"/>
        </div>
        <div className="cont">
          <h3 className="item_tit">{title}</h3>
          <p className="item_price">
            현재가<strong>{settingPrice(price)}</strong>원
          </p>
          <p className="item_price_before">최초가&nbsp;{settingPrice(price)}원</p>
        </div>
        <div className="item_tag">
          {dealType === "DIRECT_DEAL" ? (
              <img src={delImg} alt="deliveryTag"/>
          ) : (
              <img src={aucImg} alt="auctionTag"/>
          )}
        </div>
      </li>
  );
};

export default React.memo(BoardItem);
