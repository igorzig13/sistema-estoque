CREATE TABLE operation
(
    store_id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    operation_type VARCHAR(255),
    date_time      TIMESTAMP WITHOUT TIME ZONE,
    product_id     BIGINT,
    stock_id       BIGINT,
    CONSTRAINT pk_operation PRIMARY KEY (store_id)
);

CREATE TABLE product
(
    product_id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name           VARCHAR(255),
    description    OID,
    purchase_price DECIMAL(13, 2),
    sale_price     DECIMAL(13, 2),
    quantity       INTEGER,
    stock_id       BIGINT,
    CONSTRAINT pk_product PRIMARY KEY (product_id)
);

CREATE TABLE role
(
    role_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name    VARCHAR(255),
    CONSTRAINT pk_role PRIMARY KEY (role_id)
);

CREATE TABLE stock
(
    stock_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    CONSTRAINT pk_stock PRIMARY KEY (stock_id)
);

CREATE TABLE store
(
    id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name    VARCHAR(255),
    address VARCHAR(255),
    CONSTRAINT pk_store PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_roles PRIMARY KEY (role_id, user_id)
);

CREATE TABLE users
(
    user_id  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username VARCHAR(255)                            NOT NULL,
    email    VARCHAR(255)                            NOT NULL,
    password VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_username UNIQUE (username);

ALTER TABLE operation
    ADD CONSTRAINT FK_OPERATION_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (product_id);

ALTER TABLE operation
    ADD CONSTRAINT FK_OPERATION_ON_STOCK FOREIGN KEY (stock_id) REFERENCES stock (stock_id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_STOCK FOREIGN KEY (stock_id) REFERENCES stock (stock_id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES role (role_id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (user_id);