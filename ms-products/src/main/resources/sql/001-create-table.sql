CREATE TABLE products_tb
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    name             VARCHAR(255) NOT NULL,
    `description`    VARCHAR(255) NOT NULL,
    value            DECIMAL      NOT NULL,
    create_date_time datetime NULL,
    update_date_time datetime NULL,
    CONSTRAINT pk_products_tb PRIMARY KEY (id)
);

ALTER TABLE products_tb
    ADD CONSTRAINT uc_products_tb_name UNIQUE (name);