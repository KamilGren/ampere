drop schema if exists db_oze;
create schema db_oze;
use db_oze;

CREATE TABLE `inverter` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `efficiency_percent` DECIMAL(10, 2) NOT NULL,
    `phases` INTEGER NOT NULL,
    `mppt` INTEGER NOT NULL,
    `catalogue` DECIMAL(10, 2) NOT NULL,
    `net` DECIMAL(10, 2) NOT NULL,
    `gross` DECIMAL(10, 2) NOT NULL,
    `nominal_watts` DECIMAL(10, 2) NOT NULL,
    `min_watts` DECIMAL(10, 2) NOT NULL,
    `max_watts` DECIMAL(10, 2) NOT NULL,
    `percent` DECIMAL(10, 2) NOT NULL,
    `max_voltage` VARCHAR(255) NOT NULL,
    `ac_protections` VARCHAR(255) NOT NULL,
    `warranty` VARCHAR(255) NOT NULL,
    `image_id` INTEGER,
    `max_adc` DECIMAL(10, 2) NOT NULL,
    `range_dc` DECIMAL(10, 2) NOT NULL,
    `min_dc` DECIMAL(10, 2) NOT NULL,
    `thdi_hz` DECIMAL(10, 2) NOT NULL
);

CREATE TABLE `product` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `subclass` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `model` VARCHAR(255) NOT NULL,
    `manufacturer` VARCHAR(255) NOT NULL,
    `msrp` DECIMAL(10, 2) NOT NULL,
    `discount` DECIMAL(10, 2)
);

CREATE TABLE `salesman` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `password_hash` VARCHAR(255) NOT NULL,
    `role` VARCHAR(255) NOT NULL
);

CREATE TABLE `client` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    `note` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(255) NOT NULL,
    `created_at` DATETIME NOT NULL,
    `salesman_id` BIGINT NOT NULL,
    FOREIGN KEY (`salesman_id`) REFERENCES `salesman`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `building_info` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `client_id` BIGINT NOT NULL,
    `location` VARCHAR(255) NOT NULL,
    `location_temperature_c` DECIMAL(10, 2),
    `building_type_id` INTEGER NOT NULL,
    `length_m` DECIMAL(10, 2) NOT NULL,
    `width_m` DECIMAL(10, 2) NOT NULL,
    `heated_area_m2` DECIMAL(10, 2) NOT NULL,
    `ceiling_height_m` DECIMAL(10, 2) NOT NULL,
    `heating_temperature_c` DECIMAL(10, 2) NOT NULL,
    `cooling_temperature_c` DECIMAL(10, 2) NOT NULL,
    `has_basement` BOOLEAN NOT NULL,
    `wall_type_id` INTEGER NOT NULL,
    `wall_insulation_type_id` INTEGER NOT NULL,
    `roof_insulation_type_id` INTEGER NOT NULL,
    `floor_insulation_type_id` INTEGER NOT NULL,
    `wall_thickness_cm` DECIMAL(10, 2) NOT NULL,
    `wall_insulation_thickness_cm` DECIMAL(10, 2) NOT NULL,
    `roof_insulation_thickness_cm` DECIMAL(10, 2) NOT NULL,
    `floor_insulation_thickness_cm` DECIMAL(10, 2) NOT NULL,
    `window_glazing_type_id` INTEGER NOT NULL,
    `window_count` INTEGER NOT NULL,
    `exterior_door_count` INTEGER NOT NULL,
    `ventilation_type_id` INTEGER NOT NULL,
    `fuel_type_id` INTEGER NOT NULL,
    `fuel_usage_amount` DECIMAL(10, 2) NOT NULL,
    `people_count` INTEGER NOT NULL,
    `water_usage_type_id` INTEGER NOT NULL,
    FOREIGN KEY (`client_id`) REFERENCES `client`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `product_central_heating_buffer_tank` (
    `id` BIGINT PRIMARY KEY,
    `material_type_id` INTEGER NOT NULL,
    `capacity_l` INTEGER NOT NULL,
    `height_mm` INTEGER NOT NULL,
    `diameter_mm` INTEGER NOT NULL,
    `erp` VARCHAR(5) NOT NULL,
    FOREIGN KEY (`id`) REFERENCES `product`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `product_domestic_hot_water_tank` (
    `id` BIGINT PRIMARY KEY,
    `material_type_id` INTEGER NOT NULL,
    `capacity_l` INTEGER NOT NULL,
    `height_mm` INTEGER,
    `diameter_mm` INTEGER,
    `coil` VARCHAR(30) NOT NULL,
    `heater` DECIMAL(10, 2),
    `erp` VARCHAR(5) NOT NULL,
    FOREIGN KEY (`id`) REFERENCES `product`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `product_heat_pump` (
    `id` BIGINT PRIMARY KEY,
    `heat_pump_type_id` INTEGER NOT NULL,
    `indoor_unit` VARCHAR(255),
    `outdoor_unit` VARCHAR(255) NOT NULL,
    `rated_power_kw` DECIMAL(10, 2) NOT NULL,
    `heater_power_kw` DECIMAL(10, 2) NOT NULL,
    `scop` DECIMAL(10, 2) NOT NULL,
    `power_phases` INTEGER NOT NULL,
    `warranty_years` INTEGER NOT NULL,
    `heating_capacity_negative_20_c` DECIMAL(10, 2) NOT NULL,
    `heating_capacity_negative_15_c` DECIMAL(10, 2) NOT NULL,
    `heating_capacity_negative_7_c` DECIMAL(10, 2) NOT NULL,
    `heating_capacity_negative_2_c` DECIMAL(10, 2) NOT NULL,
    `heating_capacity_positive_2_c` DECIMAL(10, 2) NOT NULL,
    `heating_capacity_positive_7_c` DECIMAL(10, 2) NOT NULL,
    `heating_capacity_positive_12_c` DECIMAL(10, 2) NOT NULL,
    `heating_capacity_positive_15_c` DECIMAL(10, 2) NOT NULL,
    `heating_capacity_positive_20_c` DECIMAL(10, 2) NOT NULL,
    `energy_consumption_negative_20_c` DECIMAL(10, 2) NOT NULL,
    `energy_consumption_negative_15_c` DECIMAL(10, 2) NOT NULL,
    `energy_consumption_negative_7_c` DECIMAL(10, 2) NOT NULL,
    `energy_consumption_negative_2_c` DECIMAL(10, 2) NOT NULL,
    `energy_consumption_positive_2_c` DECIMAL(10, 2) NOT NULL,
    `energy_consumption_positive_7_c` DECIMAL(10, 2) NOT NULL,
    `energy_consumption_positive_12_c` DECIMAL(10, 2) NOT NULL,
    `energy_consumption_positive_15_c` DECIMAL(10, 2) NOT NULL,
    `energy_consumption_positive_20_c` DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (`id`) REFERENCES `product`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `product_other` (
    `id` BIGINT PRIMARY KEY,
    `type_id` INT NOT NULL,
    FOREIGN KEY (`id`) REFERENCES `product`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `tax_credit` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `percentage` DECIMAL(10, 2) NOT NULL
);

CREATE TABLE `financial_program` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `percentage` DECIMAL(10, 2) NOT NULL,
    `limit` DECIMAL(10, 2) NOT NULL,
    `condition` VARCHAR(1000) NOT NULL
);

CREATE TABLE `contract` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `building_info_id` BIGINT NOT NULL,
    `salesman_id` BIGINT NOT NULL,
    `client_id` BIGINT NOT NULL,
    `material_cost` DECIMAL(10, 2) NOT NULL,
    `labor_cost` DECIMAL(10, 2) NOT NULL,
    `markup` DECIMAL(10, 2) NOT NULL,
    `tax_rate` DECIMAL(10, 2) NOT NULL,
    `margin` DECIMAL(10, 2) NOT NULL,
    `tax_credit_id` BIGINT,
    `financial_program_id` BIGINT,
    `created_at` DATETIME NOT NULL,
    FOREIGN KEY (`building_info_id`) REFERENCES `building_info`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`salesman_id`) REFERENCES `salesman`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`client_id`) REFERENCES `client`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`tax_credit_id`) REFERENCES `tax_credit`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`financial_program_id`) REFERENCES `financial_program`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `contract_quantity_co_buffer` (
    `product_id` BIGINT NOT NULL,
    `contract_id` BIGINT NOT NULL,
    `quantity` INT NOT NULL,
    PRIMARY KEY (`product_id`, `contract_id`),
    FOREIGN KEY (`product_id`) REFERENCES `product_central_heating_buffer_tank`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`contract_id`) REFERENCES `contract`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `contract_quantity_cwu_tank` (
    `product_id` BIGINT NOT NULL,
    `contract_id` BIGINT NOT NULL,
    `quantity` INT NOT NULL,
    PRIMARY KEY (`product_id`, `contract_id`),
    FOREIGN KEY (`product_id`) REFERENCES `product_domestic_hot_water_tank`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`contract_id`) REFERENCES `contract`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `contract_quantity_heat_pump` (
    `product_id` BIGINT NOT NULL,
    `contract_id` BIGINT NOT NULL,
    `quantity` INT NOT NULL,
    PRIMARY KEY (`product_id`, `contract_id`),
    FOREIGN KEY (`product_id`) REFERENCES `product_heat_pump`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`contract_id`) REFERENCES `contract`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `contract_quantity_other` (
    `product_id` BIGINT NOT NULL,
    `contract_id` BIGINT NOT NULL,
    `quantity` INT NOT NULL,
    PRIMARY KEY (`product_id`, `contract_id`),
    FOREIGN KEY (`product_id`) REFERENCES `product_other`(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`contract_id`) REFERENCES `contract`(`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO salesman (first_name, last_name, username, email, password_hash, `role`)
VALUES ('foo', 'bar', 'user', 'foobar@gmail.com', '$2a$10$StYjK5xVUax5oo1uc5ryl.FvsUcz08yFbp7gvzx/AfLsyWGXseiiG', 'HANDLOWIEC'),
       ('admin', 'admin', 'admin', 'foobar@gmail.com', '$2a$10$StYjK5xVUax5oo1uc5ryl.FvsUcz08yFbp7gvzx/AfLsyWGXseiiG', 'ADMIN');
