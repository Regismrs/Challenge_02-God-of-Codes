CREATE DATABASE IF NOT EXISTS db_ecommerce;
USE db_ecommerce;
CREATE TABLE IF NOT exists products_tb
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    name             VARCHAR(255) NOT NULL,
    `description`    VARCHAR(255) NOT NULL,
    value            DECIMAL(38,2) NOT NULL,
    create_date_time datetime NULL,
    update_date_time datetime NULL,
    CONSTRAINT pk_products_tb PRIMARY KEY (id),
    CONSTRAINT uc_products_tb_name UNIQUE (name)
);
