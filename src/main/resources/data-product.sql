--상품
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(PARSEDATETIME ('2016-11-17 12:00:00','yyyy-MM-dd hh:mm:ss'), PARSEDATETIME ('2016-11-17 12:00:00','yyyy-MM-dd hh:mm:ss'), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 14, 1);

INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE,
                    PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
VALUES (CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW',
        'SALE', '제목', 15, 1);

INSERT INTO PRODUCT_IMAGE(CREATED_AT, UPDATED_AT, PRODUCT_ID, IMAGE_URL)
VALUES (CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1,
        'https://ccimg.hellomarket.com/images/2021/item/02/25/17/4412489_3221733_1.jpg?size=s4');

INSERT INTO PRODUCT_IMAGE(CREATED_AT, UPDATED_AT, PRODUCT_ID, IMAGE_URL)
VALUES (CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1,
        'https://ccimg.hellomarket.com/images/2021/item/02/24/18/4500802_743986_2.jpg?size=s4');

INSERT INTO PRODUCT_IMAGE(CREATED_AT, UPDATED_AT, PRODUCT_ID, IMAGE_URL)
VALUES (CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 1,
        'https://ccimg.hellomarket.com/images/2021/item/02/25/13/0034005_5250989_1.jpg?size=s4');

---


-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ONLINE', 'DIRECT_PICKUP', '설명', 10000, 'OLD', 'SALE', '제목', 16, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 17, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 18, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 19, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ONLINE', 'DIRECT_PICKUP', '설명', 10000, 'OLD', 'SALE', '제목', 20, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- ---- 10개
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(PARSEDATETIME ('2016-11-17 12:00:00','yyyy-MM-dd hh:mm:ss'), PARSEDATETIME ('2016-11-17 12:00:00','yyyy-MM-dd hh:mm:ss'), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 14, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 15, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ONLINE', 'DIRECT_PICKUP', '설명', 10000, 'OLD', 'SALE', '제목', 16, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 17, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 18, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 19, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ONLINE', 'DIRECT_PICKUP', '설명', 10000, 'OLD', 'SALE', '제목', 20, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- ---- 20개
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(PARSEDATETIME ('2016-11-17 12:00:00','yyyy-MM-dd hh:mm:ss'), PARSEDATETIME ('2016-11-17 12:00:00','yyyy-MM-dd hh:mm:ss'), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 14, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 15, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ONLINE', 'DIRECT_PICKUP', '설명', 10000, 'OLD', 'SALE', '제목', 16, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 17, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 18, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 19, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ONLINE', 'DIRECT_PICKUP', '설명', 10000, 'OLD', 'SALE', '제목', 20, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- ---- 30개
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(PARSEDATETIME ('2016-11-17 12:00:00','yyyy-MM-dd hh:mm:ss'), PARSEDATETIME ('2016-11-17 12:00:00','yyyy-MM-dd hh:mm:ss'), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 14, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 15, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ONLINE', 'DIRECT_PICKUP', '설명', 10000, 'OLD', 'SALE', '제목', 16, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 17, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 18, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 19, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ONLINE', 'DIRECT_PICKUP', '설명', 10000, 'OLD', 'SALE', '제목', 20, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- ---- 40개
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(PARSEDATETIME ('2016-11-17 12:00:00','yyyy-MM-dd hh:mm:ss'), PARSEDATETIME ('2016-11-17 12:00:00','yyyy-MM-dd hh:mm:ss'), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 14, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 15, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ONLINE', 'DIRECT_PICKUP', '설명', 10000, 'OLD', 'SALE', '제목', 16, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 17, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 18, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'DIRECT_DEAL', 'PARCEL', '설명', 10000, 'NEW', 'SALE', '제목', 19, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'ONLINE', 'DIRECT_PICKUP', '설명', 10000, 'OLD', 'SALE', '제목', 20, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);
--
-- INSERT INTO PRODUCT(CREATED_AT, UPDATED_AT, DEAL_TYPE, DELIVERY_TYPE, DESCRIPTION, PRICE, PRODUCT_STATUS, SALES_STATUS, TITLE, CATEGORY_ID, USER_ID)
-- VALUES(CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'NO_SELL', 'DIRECT_PICKUP', '설명', 10000, 'USED', 'SALE', '제목', 21, 1);

---- 50개