CREATE DATABASE IF NOT EXISTS db_ecommerce;
DROP TABLE IF EXISTS `order_product_tb`;
DROP TABLE IF EXISTS `order_tb`;

CREATE TABLE `order_tb` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address_city` varchar(255) DEFAULT NULL,
  `address_complement` varchar(255) DEFAULT NULL,
  `address_number` varchar(255) DEFAULT NULL,
  `address_postal_code` varchar(255) DEFAULT NULL,
  `address_state` varchar(255) DEFAULT NULL,
  `address_street` varchar(255) DEFAULT NULL,
  `cancel_date` datetime(6) DEFAULT NULL,
  `cancel_reason` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `discount` decimal(38,2) NOT NULL,
  `payment_method` enum('BANK_TRANSFER','CREDIT_CARD','CRYPTOCURRENCY','GIFT_CARD','OTHER','PIX') NOT NULL,
  `status` enum('CANCELED','CONFIRMED','SENT') NOT NULL,
  `sub_total_value` decimal(38,2) NOT NULL DEFAULT '0.00',
  `total_value` decimal(38,2) NOT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `order_tb_chk_1` CHECK ((`discount` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO order_tb (address_city, address_complement, address_number, address_postal_code, address_state, address_street, cancel_date, cancel_reason, created_date, discount, payment_method, status, sub_total_value, total_value)
VALUES
('City1', 'Complement1', '123', '12345', 'State1', 'Street1', NULL, NULL, NOW(), 10.00, 'CREDIT_CARD', 'CONFIRMED', 100.00, 90.00),
('City2', 'Complement2', '456', '67890', 'State2', 'Street2', NULL, NULL, NOW(), 5.00, 'BANK_TRANSFER', 'CONFIRMED', 50.00, 45.00),
('City3', 'Complement3', '789', '54321', 'State3', 'Street3', NULL, NULL, NOW(), 20.00, 'CRYPTOCURRENCY', 'SENT', 200.00, 180.00),
('City4', 'Complement4', '101', '98765', 'State4', 'Street4', NULL, NULL, NOW(), 15.00, 'CREDIT_CARD', 'CONFIRMED', 150.00, 135.00),
('City5', 'Complement5', '202', '24680', 'State5', 'Street5', NULL, NULL, NOW(), 0.00, 'PIX', 'CONFIRMED', 30.00, 30.00),
('City6', 'Complement6', '303', '13579', 'State6', 'Street6', NULL, NULL, NOW(), 12.00, 'BANK_TRANSFER', 'CONFIRMED', 120.00, 108.00),
('City7', 'Complement7', '404', '86420', 'State7', 'Street7', NULL, NULL, NOW(), 8.00, 'PIX', 'CONFIRMED', 80.00, 72.00),
('City8', 'Complement8', '505', '97531', 'State8', 'Street8', NULL, NULL, NOW(), 10.00, 'CRYPTOCURRENCY', 'CONFIRMED', 100.00, 90.00),
('City9', 'Complement9', '606', '54321', 'State9', 'Street9', NULL, NULL, NOW(), 25.00, 'CREDIT_CARD', 'CONFIRMED', 250.00, 225.00),
('City10', 'Complement10', '707', '12345', 'State10', 'Street10', NULL, NULL, NOW(), 7.00, 'PIX', 'CONFIRMED', 70.00, 63.00);


CREATE TABLE IF NOT EXISTS `order_product_tb` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `value` decimal(38,2) DEFAULT NULL,
  `order_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_order_product` (`order_id`),
  CONSTRAINT `FK_order_product` FOREIGN KEY (`order_id`) REFERENCES `order_tb` (`id`),
  CONSTRAINT `order_product_tb_chk_1` CHECK ((`quantity` >= 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO order_product_tb (id, product_id, quantity, `value`, order_id)
VALUES
(null, 1, 3, 30.00, 1),
(null,2, 2, 20.00, 1),
(null,3, 1, 10.00, 2),
(null,4, 4, 40.00, 2),
(null,5, 2, 20.00, 3),
(null,6, 3, 30.00, 3), 
(null,7, 2, 20.00, 4), 
(null,8, 1, 10.00, 5),
(null,9, 4, 40.00, 5), 
(null,10, 3, 30.00, 6),
(null,1, 2, 20.00, 6), 
(null,2, 3, 30.00, 7), 
(null,3, 2, 20.00, 7), 
(null,4, 1, 10.00, 8), 
(null,5, 4, 40.00, 8), 
(null,6, 3, 30.00, 9), 
(null,7, 2, 20.00, 9), 
(null,8, 1, 10.00, 10),
(null,9, 4, 40.00, 10),
(null,10, 3, 30.00, 10);
