import React from "react";

function settingPrice(price) {
  if (!(price instanceof Number)) {
    price = parseInt(price);
  }
  price += "";
  return price.replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

const BoardItem = ({ title, price, dealType, image_url }) => {
  return (
    <li className="main_con">
      <div className="item_box">
        <img src={image_url} alt="IMAGE" />
        <p className="dealer_position">
          {dealType === "DIRECT_DEAL" && (
            <img src="http://localhost:3000/img/delivery_icon.svg" />
          )}
        </p>
        {dealType === "NO_SELL" && (
          <div className="bid_time">
            <p>
              <i className="fas fa-stopwatch"></i>2/6 22:00
            </p>
          </div>
        )}
      </div>
      <div className="cont">
        <div className="item_title">{title}</div>
        <div className="item_price">
          현재가<strong>{settingPrice(price)}</strong>원
        </div>
      </div>
    </li>
  );
};

export default React.memo(BoardItem);
