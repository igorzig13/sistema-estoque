ALTER TABLE operations
DROP
CONSTRAINT fk_operations_on_stock;

ALTER TABLE products
DROP
CONSTRAINT fk_products_on_stock;

ALTER TABLE stores
DROP
CONSTRAINT fk_stores_on_stock;

ALTER TABLE operations
    ADD store_id BIGINT;

ALTER TABLE products
    ADD store_id BIGINT;

ALTER TABLE operations
    ADD CONSTRAINT FK_OPERATIONS_ON_STORE FOREIGN KEY (store_id) REFERENCES stores (store_id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_STORE FOREIGN KEY (store_id) REFERENCES stores (store_id);

DROP TABLE stocks CASCADE;

ALTER TABLE operations
DROP
COLUMN stock_id;

ALTER TABLE products
DROP
COLUMN stock_id;

ALTER TABLE stores
DROP
COLUMN stock_id;