-- DROP TABLE sales;

CREATE SEQUENCE sales_id_seq INCREMENT BY 1;

CREATE TABLE sales
(
  id            BIGINT DEFAULT nextval('sales_id_seq' :: REGCLASS) NOT NULL,
  shop_id       INTEGER                                            NOT NULL,
  sale_date     BIGINT                                             NOT NULL,
  product_id    INTEGER                                            NOT NULL,
  product_count INTEGER DEFAULT 0                                  NOT NULL,
  price         DOUBLE PRECISION                                   NOT NULL,
  category_id   INTEGER                                            NOT NULL,
  vendor_id     INTEGER                                            NOT NULL
) WITHOUT OIDS;

ALTER TABLE sales
  ADD CONSTRAINT pksales PRIMARY KEY (id);
CREATE INDEX idx_sales_shop_id
  ON sales USING BTREE (shop_id);
CREATE INDEX idx_sales_product_id
  ON sales USING BTREE (product_id);
CREATE INDEX idx_sales_price
  ON sales USING BTREE (price);


INSERT INTO sales (shop_id, sale_date, product_id, product_count, price, category_id, vendor_id)
VALUES
  (1, (extract(epoch from now())::BIGINT * 1000), 1, 40, 5.00, 1, 7),
  (1, (extract(epoch from now())::BIGINT * 1000), 1, 40, 5.00, 1, 7);
