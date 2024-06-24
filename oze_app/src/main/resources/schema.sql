CREATE TABLE `building_info` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
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
    `water_usage_type_id` INTEGER NOT NULL
);

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

CREATE TABLE `product_central_heating_buffer_tank` (
    `id` BIGINT PRIMARY KEY,
    `material_type_id` INTEGER NOT NULL,
    `capacity_l` INTEGER NOT NULL,
    `height_mm` INTEGER NOT NULL,
    `diameter_mm` INTEGER NOT NULL,
    `erp` VARCHAR(5) NOT NULL,
    FOREIGN KEY (`id`) REFERENCES `product`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `product_circulation` (
    `id` BIGINT PRIMARY KEY,
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

CREATE TABLE `product_heating_circuit` (
    `id` BIGINT PRIMARY KEY,
    FOREIGN KEY (`id`) REFERENCES `product`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);
5
CREATE TABLE `product_wifi_module` (
    `id` BIGINT PRIMARY KEY,
    FOREIGN KEY (`id`) REFERENCES `product`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `order_config` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `heat_pump_id` BIGINT NOT NULL,
    `cwu_tank_id` BIGINT,
    `co_buffer_id` BIGINT,
    `heating_circuit_id` BIGINT NOT NULL,
    `circulation_id` BIGINT,
    `wifi_module_id` BIGINT NOT NULL,
    FOREIGN KEY (`heat_pump_id`) REFERENCES `product_heat_pump`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`cwu_tank_id`) REFERENCES `product_domestic_hot_water_tank`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`co_buffer_id`) REFERENCES `product_central_heating_buffer_tank`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`heating_circuit_id`) REFERENCES `product_heating_circuit`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`circulation_id`) REFERENCES `product_circulation`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`wifi_module_id`) REFERENCES `product_wifi_module`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE `order` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `building_info_id` BIGINT NOT NULL,
    `salesman_id` BIGINT NOT NULL,
    `client_id` BIGINT NOT NULL,
    `order_config_id` BIGINT NOT NULL,
    `created_at` DATETIME NOT NULL,
    FOREIGN KEY (`building_info_id`) REFERENCES `building_info`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`salesman_id`) REFERENCES `salesman`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`client_id`) REFERENCES `client`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (`order_config_id`) REFERENCES `order_config`(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
);
