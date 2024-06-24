INSERT INTO `tax_credit` (`percentage`)
VALUES ('0.12'), ('0.32');

INSERT INTO financial_program
    (`name`, `percentage`, `limit`, `condition`)
VALUES
    ('Czyste powietrze podstawowy PC', '19400.00', '0.55', 'Wymiana starego kotła. Dochód do 135tyś. Brutto'),
    ('Czyste powietrze podstawowy PC + CO', '27500.00', '0.55', 'Wymiana starego kotła. Dochód do 135tyś. Brutto'),
    ('Czyste powietrze podwyższony PC', '28100.00', '0.8', 'Wymiana starego kotła. Miesięczny dochód na gospodarstwo jednoosobowe 2651 zł brutto, na gospodarstwo wieloosobowe 1894 brutto'),
    ('Czyste powietrze podwyższony PC + CO', '42400.00', '0.8', 'Wymiana starego kotła. Miesięczny dochód na gospodarstwo jednoosobowe 2651 zł brutto, na gospodarstwo wieloosobowe 1894 brutto'),
    ('Czyste powietrze najwyższy PC', '35200.00', '1.0', 'Wymiana starego kotła. Miesięczny dochód na gospodarstwo jednoosobowe 1526 zł brutto, na gospodarstwo wieloosobowe 1090 brutto'),
    ('Czyste powietrze najwyższy PC + CO', '55600.00', '1.0', 'Wymiana starego kotła. Miesięczny dochód na gospodarstwo jednoosobowe 1526 zł brutto, na gospodarstwo wieloosobowe 1090 brutto');


INSERT INTO product
    (`subclass`, `name`, `model`, `manufacturer`, `msrp`, `discount`)
VALUES
    ('DomesticHotWaterTank', 'Nierdzewny 200l', 'PAW-TD20C1E5', 'Panasonic', '6918.00', '0.47'),
    ('DomesticHotWaterTank', 'Nierdzewny 300l', 'PAW-TD30C1E5', 'Panasonic', '8211.00', '0.47'),
    ('DomesticHotWaterTank', 'Standard 200l', 'W15 200', 'Weber', '2810.00', '0.18'),
    ('DomesticHotWaterTank', 'Standard 300l', 'W15 300', 'Weber', '3270.00', '0.18'),
    ('DomesticHotWaterTank', 'Standard 300l biwalentny', 'W16 300', 'Weber', '5167.00', '0.18'),
    ('DomesticHotWaterTank', 'Standard 200l wąski', 'W15 200V', 'Weber', '2154.47', NULL),
    ('DomesticHotWaterTank', 'Nierdzewny 200l ', 'OSHW-200F', 'LG', '4940.00', '0.1'),
    ('DomesticHotWaterTank', 'Nierdzewny 300l', 'OSHW-300F', 'LG', '5970.00', '0.1'),
    ('DomesticHotWaterTank', 'Nierdzewny 300l biwalentny', 'OSHW-300FD', 'LG', '6180.00', '0.1'),
    ('DomesticHotWaterTank', 'Nierdzewny 150l', 'EKHWSP150D3V3', 'Daikin', '4739.00', NULL),
    ('DomesticHotWaterTank', 'Nierdzewny 180l', 'EKHWSP180D3V3', 'Daikin', '4851.00', NULL),
    ('DomesticHotWaterTank', 'Nierdzewny 200l', 'EKHWSP200D3V3', 'Daikin', '5068.00', NULL),
    ('DomesticHotWaterTank', 'Nierdzewny 250l', 'EKHWSP250D3V3', 'Daikin', '5320.00', NULL),
    ('DomesticHotWaterTank', 'Nierdzewny 300l', 'EKHWSP300D3V3', 'Daikin', '5328.00', NULL),
    ('DomesticHotWaterTank', 'Nierdzewny 300l biwalentny', 'EKHWP300B', 'Daikin', '7868.00', NULL);

INSERT INTO product_domestic_hot_water_tank
    (`id`, `material_type_id`, `capacity_l`, `height_mm`, `diameter_mm`, `coil`, `heater`, `erp`)
VALUES
    (1, 2, 192, 1270, 595, '1.8', NULL, 'A'),
    (2, 2, 280, 1750, 595, '1.8', NULL, 'A'),
    (3, 1, 200, NULL, NULL, '1.9', NULL, 'B'),
    (4, 1, 300, NULL, NULL, '3.2', NULL, 'B'),
    (5, 1, 300, NULL, NULL, '2,6 + 1,2', NULL, 'B'),
    (6, 1, 200, NULL, NULL, '1.9', NULL, 'B'),
    (7, 2, 200, 1350, 640, '2.3', '2.4', 'B'),
    (8, 2, 200, 1850, 640, '3.1', '2.4', 'B'),
    (9, 2, 300, 1850, 640, '3,1 + 0,97', '2.4', 'B'),
    (10, 2, 150, 1000, NULL, '1.05', '3', 'B'),
    (11, 2, 180, 1164, NULL, '1.4', '3', 'B'),
    (12, 2, 200, 1264, NULL, '1.8', '3', 'B'),
    (13, 2, 250, 1535, NULL, '1.8', '3', 'B'),
    (14, 2, 300, 1745, NULL, '1.8', '3', 'B'),
    (15, 2, 300, 1646, 595, '5,6+2,66', NULL, 'B');


INSERT INTO product
(`subclass`, `name`, `model`, `manufacturer`, `msrp`, `discount`)
VALUES
    ('CentralHeatingBufferTank', 'Nierdzewny 50l', 'PAW-BTANK50L-2', 'Panasonic / OSO', '1874.00', '0.47'),
    ('CentralHeatingBufferTank', 'Nierdzewny 100l', 'PAW-BTANK100L', 'Panasonic / OSO', '2928.00', '0.47'),
    ('CentralHeatingBufferTank', 'Standard 80l wiszący', 'W4B 80', 'Weber', '970.00', '0.18'),
    ('CentralHeatingBufferTank', 'Standard 100l stojący', 'W4 100', 'Weber', '1130.00', '0.18'),
    ('CentralHeatingBufferTank', 'Standard 200l stojący', 'W4 200', 'Weber', '1670.00', '0.18'),
    ('CentralHeatingBufferTank', 'Standard 300l stojący', 'W4 300', 'Weber', '2255.00', '0.18');

INSERT INTO product_central_heating_buffer_tank
(`id`, `material_type_id`, `capacity_l`, `height_mm`, `diameter_mm`, `erp`)
VALUES
    (16, 2, 50, 636, 430, 'B'),
    (17, 2, 100, 1175, 430, 'C'),
    (18, 3, 80, 747, 440, 'B'),
    (19, 3, 100, 906, 471, 'B'),
    (20, 3, 200, 1306, 607, 'B'),
    (21, 3, 300, 1462, 657, 'B');

INSERT INTO product
(`subclass`, `name`, `model`, `manufacturer`, `msrp`, `discount`)
VALUES
    ('OtherProduct', 'Grundfos Alpha 1 25-60', 'Grundfos Alpha 1 25-60', 'Grundfos', '800.0', NULL),
    ('OtherProduct', 'Wilo STAR Z NOVA', 'Wilo STAR Z NOVA', 'Wilo', '800.0', NULL),
    ('OtherProduct', 'CZ-TAW1', 'CZ-TAW1', 'Panasonic', '785.0', '0.47'),
    ('OtherProduct', 'PWMFDD200', 'PWMFDD200', 'LG', '550.0', '0.50'),
    ('OtherProduct', 'BRP069A62', 'BRP069A62', 'Daikin', '441.0', NULL),
    ('OtherProduct', 'N/A', 'Heat Pump Base', 'N/A', '1000.0', NULL),
    ('OtherProduct', 'AFRISO ADS 160', 'Separator AFRISO ADS 160', 'N/A', '600.0', NULL);


INSERT INTO product_other
(`id`, `type_id`)
VALUES
    (22, 3),
    (23, 1),
    (24, 5),
    (25, 5),
    (26, 5),
    (27, 2),
    (28, 4);

INSERT INTO product
(`subclass`, `name`, `model`, `manufacturer`, `msrp`, `discount`)
VALUES
('HeatPump', 'High Performance 3kW Split 1f', 'High Performance 3kW Split 1f', 'Panasonic', '22526', '0.47'),
('HeatPump', 'High Performance 5kW Split 1f', 'High Performance 5kW Split 1f', 'Panasonic', '23461', '0.47'),
('HeatPump', 'High Performance 7kW Split 1f', 'High Performance 7kW Split 1f', 'Panasonic', '24875', '0.47'),
('HeatPump', 'High Performance 9kW Split 1f', 'High Performance 9kW Split 1f', 'Panasonic', '27164', '0.47'),
('HeatPump', 'High Performance 12kW Split 1f', 'High Performance 12kW Split 1f', 'Panasonic', '31519', '0.47'),
('HeatPump', 'High Performance 12kW Split 3f', 'High Performance 12kW Split 3f', 'Panasonic', '32822', '0.47'),
('HeatPump', 'High Performance 16kW Split 1f', 'High Performance 16kW Split 1f', 'Panasonic', '37731', '0.47'),
('HeatPump', 'High Performance 16kW Split 3f', 'High Performance 16kW Split 3f', 'Panasonic', '39374', '0.47'),
('HeatPump', 'T-CAP 9kW Split 1f', 'T-CAP 9kW Split 1f', 'Panasonic', '32350', '0.47'),
('HeatPump', 'T-CAP 9kW Split 3f', 'T-CAP 9kW Split 3f', 'Panasonic', '35795', '0.47'),
('HeatPump', 'T-CAP 12kW Split 1f', 'T-CAP 12kW Split 1f', 'Panasonic', '37726', '0.47'),
('HeatPump', 'T-CAP 12kW Split 3f', 'T-CAP 12kW Split 3f', 'Panasonic', '40971', '0.47'),
('HeatPump', 'T-CAP 16kW Split 3f', 'T-CAP 16kW Split 3f', 'Panasonic', '44306', '0.47'),
('HeatPump', 'High Performance 3kW AiO 1f', 'High Performance 3kW AiO 1f', 'Panasonic', '32413', '0.47'),
('HeatPump', 'High Performance 5kW AiO 1f', 'High Performance 5kW AiO 1f', 'Panasonic', '33348', '0.47'),
('HeatPump', 'High Performance 7kW AiO 1f', 'High Performance 7kW AiO 1f', 'Panasonic', '34762', '0.47'),
('HeatPump', 'High Performance 9kW AiO 1f', 'High Performance 9kW AiO 1f', 'Panasonic', '37051', '0.47'),
('HeatPump', 'High Performance 12kW AiO 1f', 'High Performance 12kW AiO 1f', 'Panasonic', '41562', '0.47'),
('HeatPump', 'High Performance 12kW AiO 3f', 'High Performance 12kW AiO 3f', 'Panasonic', '42927', '0.47'),
('HeatPump', 'High Performance 16kW AiO 1f', 'High Performance 16kW AiO 1f', 'Panasonic', '44927', '0.47'),
('HeatPump', 'High Performance 16kW AiO 3f', 'High Performance 16kW AiO 3f', 'Panasonic', '45508', '0.47'),
('HeatPump', 'T-CAP 9kW AiO 1f', 'T-CAP 9kW AiO 1f', 'Panasonic', '44018', '0.47'),
('HeatPump', 'T-CAP 9kW AiO 3f', 'T-CAP 9kW AiO 3f', 'Panasonic', '46283', '0.47'),
('HeatPump', 'T-CAP 12kW AiO 1f', 'T-CAP 12kW AiO 1f', 'Panasonic', '46991', '0.47'),
('HeatPump', 'T-CAP 12kW AiO 3f', 'T-CAP 12kW AiO 3f', 'Panasonic', '48622', '0.47'),
('HeatPump', 'T-CAP 16kW AiO 3f', 'T-CAP 16kW AiO 3f', 'Panasonic', '48825', '0.47'),
('HeatPump', 'High Performance 5kW Mono 1f', 'High Performance 5kW Mono 1f', 'Panasonic', '21090', '0.47'),
('HeatPump', 'High Performance 7kW Mono 1f', 'High Performance 7kW Mono 1f', 'Panasonic', '22082', '0.47'),
('HeatPump', 'High Performance 9kW Mono 1f', 'High Performance 9kW Mono 1f', 'Panasonic', '26589', '0.47'),
('HeatPump', 'High Performance 12kW Mono 1f', 'High Performance 12kW Mono 1f', 'Panasonic', '28978', '0.47'),
('HeatPump', 'High Performance 16kW Mono 1f', 'High Performance 16kW Mono 1f', 'Panasonic', '30946', '0.47'),
('HeatPump', 'T-CAP 9kW Mono 1f', 'T-CAP 9kW Mono 1f', 'Panasonic', '28437', '0.47'),
('HeatPump', 'T-CAP 9kW Mono 3f', 'T-CAP 9kW Mono 3f', 'Panasonic', '32354', '0.47'),
('HeatPump', 'T-CAP 12kW Mono 1f', 'T-CAP 12kW Mono 1f', 'Panasonic', '34118', '0.47'),
('HeatPump', 'T-CAP 12kW Mono 3f', 'T-CAP 12kW Mono 3f', 'Panasonic', '39414', '0.47'),
('HeatPump', 'T-CAP 16kW Mono 3f', 'T-CAP 16kW Mono 3f', 'Panasonic', '44489', '0.47'),
('HeatPump', 'ThermaV 4kW Split 1f', 'ThermaV 4kW Split 1f', 'LG', '20000', '0.5'),
('HeatPump', 'ThermaV 5kW Split 1f', 'ThermaV 5kW Split 1f', 'LG', '22200', '0.5'),
('HeatPump', 'ThermaV 6kW Split 1f', 'ThermaV 6kW Split 1f', 'LG', '20500', '0.5'),
('HeatPump', 'ThermaV 7kW Split 1f', 'ThermaV 7kW Split 1f', 'LG', '22600', '0.5'),
('HeatPump', 'ThermaV 9kW Split 1f', 'ThermaV 9kW Split 1f', 'LG', '23500', '0.5'),
('HeatPump', 'ThermaV 12kW Split 1f', 'ThermaV 12kW Split 1f', 'LG', '28200', '0.5'),
('HeatPump', 'ThermaV 12kW Split 3f', 'ThermaV 12kW Split 3f', 'LG', '29400', '0.5'),
('HeatPump', 'ThermaV 14kW Split 1f', 'ThermaV 14kW Split 1f', 'LG', '30100', '0.5'),
('HeatPump', 'ThermaV 14kW Split 3f', 'ThermaV 14kW Split 3f', 'LG', '31500', '0.5'),
('HeatPump', 'ThermaV 16kW Split 1f', 'ThermaV 16kW Split 1f', 'LG', '32600', '0.5'),
('HeatPump', 'ThermaV 16kW Split 3f', 'ThermaV 16kW Split 3f', 'LG', '34000', '0.5'),
('HeatPump', 'ThermaV 4kW IWT 1f', 'ThermaV 4kW IWT 1f', 'LG', '31000', '0.5'),
('HeatPump', 'ThermaV 5kW IWT 1f', 'ThermaV 5kW IWT 1f', 'LG', '34400', '0.5'),
('HeatPump', 'ThermaV 6kW IWT 1f', 'ThermaV 6kW IWT 1f', 'LG', '31500', '0.5'),
('HeatPump', 'ThermaV 7kW IWT 1f', 'ThermaV 7kW IWT 1f', 'LG', '34800', '0.5'),
('HeatPump', 'ThermaV 9kW IWT 1f', 'ThermaV 9kW IWT 1f', 'LG', '35700', '0.5'),
('HeatPump', 'ThermaV 12kW IWT 1f', 'ThermaV 12kW IWT 1f', 'LG', '46000', '0.5'),
('HeatPump', 'ThermaV 12kW IWT 3f', 'ThermaV 12kW IWT 3f', 'LG', '47200', '0.5'),
('HeatPump', 'ThermaV 14kW IWT 1f', 'ThermaV 14kW IWT 1f', 'LG', '47900', '0.5'),
('HeatPump', 'ThermaV 14kW IWT 3f', 'ThermaV 14kW IWT 3f', 'LG', '49300', '0.5'),
('HeatPump', 'ThermaV 16kW IWT 1f', 'ThermaV 16kW IWT 1f', 'LG', '50400', '0.5'),
('HeatPump', 'ThermaV 16kW IWT 3f', 'ThermaV 16kW IWT 3f', 'LG', '51800', '0.5'),
('HeatPump', 'Silence Supreme 5kW 1f', 'Silence Supreme 5kW 1f', 'LG', '23800', '0.5'),
('HeatPump', 'Silence Supreme 7kW 1f', 'Silence Supreme 7kW 1f', 'LG', '24900', '0.5'),
('HeatPump', 'Silence Supreme 9kW 1f', 'Silence Supreme 9kW 1f', 'LG', '27700', '0.5'),
('HeatPump', 'Silence Supreme 9kW 3f', 'Silence Supreme 9kW 3f', 'LG', '29700', '0.5'),
('HeatPump', 'Silence Supreme 12kW 1f', 'Silence Supreme 12kW 1f', 'LG', '31000', '0.5'),
('HeatPump', 'Silence Supreme 12kW 3f', 'Silence Supreme 12kW 3f', 'LG', '33600', '0.5'),
('HeatPump', 'Silence Supreme 14kW 1f', 'Silence Supreme 14kW 1f', 'LG', '33000', '0.5'),
('HeatPump', 'Silence Supreme 14kW 3f', 'Silence Supreme 14kW 3f', 'LG', '36500', '0.5'),
('HeatPump', 'Silence Supreme 16kW 1f', 'Silence Supreme 16kW 1f', 'LG', '36900', '0.5'),
('HeatPump', 'Silence Supreme 16kW 3f', 'Silence Supreme 16kW 3f', 'LG', '38600', '0.5'),
('HeatPump', 'Altherma 3 R 4kW split 1f', 'Altherma 3 R 4kW split 1f', 'Daikin', '24890', '0.3'),
('HeatPump', 'Altherma 3 R 6kW split 1f', 'Altherma 3 R 6kW split 1f', 'Daikin', '28940', '0.3'),
('HeatPump', 'Altherma 3 R 8kW split 1f', 'Altherma 3 R 8kW split 1f', 'Daikin', '31580', '0.3'),
('HeatPump', 'Altherma 3 R 11kW split 1f', 'Altherma 3 R 11kW split 1f', 'Daikin', '36240', '0.3'),
('HeatPump', 'Altherma 3 R 11kW split 3f', 'Altherma 3 R 11kW split 3f', 'Daikin', '37640', '0.3'),
('HeatPump', 'Altherma 3 R 14kW split 1f', 'Altherma 3 R 14kW split 1f', 'Daikin', '40580', '0.3'),
('HeatPump', 'Altherma 3 R 14kW split 3f', 'Altherma 3 R 14kW split 3f', 'Daikin', '45250', '0.3'),
('HeatPump', 'Altherma 3 R 16kW split 1f', 'Altherma 3 R 16kW split 1f', 'Daikin', '43430', '0.3'),
('HeatPump', 'Altherma 3 R 16kW split 3f', 'Altherma 3 R 16kW split 3f', 'Daikin', '48430', '0.3'),
('HeatPump', 'Altherma 3 R 4kW AiO 1f', 'Altherma 3 R 4kW AiO 1f', 'Daikin', '31740', '0.3'),
('HeatPump', 'Altherma 3 R 6kW AiO 1f', 'Altherma 3 R 6kW AiO 1f', 'Daikin', '35230', '0.3'),
('HeatPump', 'Altherma 3 R 8kW AiO 1f', 'Altherma 3 R 8kW AiO 1f', 'Daikin', '37870', '0.3'),
('HeatPump', 'Altherma 3 R 11kW AiO 1f', 'Altherma 3 R 11kW AiO 1f', 'Daikin', '44310', '0.33'),
('HeatPump', 'Altherma 3 R 11kW AiO 3f', 'Altherma 3 R 11kW AiO 3f', 'Daikin', '44590', '0.33'),
('HeatPump', 'Altherma 3 R 14kW AiO 1f', 'Altherma 3 R 14kW AiO 1f', 'Daikin', '48410', '0.33'),
('HeatPump', 'Altherma 3 R 14kW AiO 3f', 'Altherma 3 R 14kW AiO 3f', 'Daikin', '52180', '0.33'),
('HeatPump', 'Altherma 3 R 16kW AiO 1f', 'Altherma 3 R 16kW AiO 1f', 'Daikin', '51260', '0.33'),
('HeatPump', 'Altherma 3 R 16kW AiO 3f', 'Altherma 3 R 16kW AiO 3f', 'Daikin', '55360', '0.33'),
('HeatPump', 'Altherma 3 R 4kW Mono 1f', 'Altherma 3 R 4kW Mono 1f', 'Daikin', '19920', '0.3'),
('HeatPump', 'Altherma 3 R 6kW Mono 1f', 'Altherma 3 R 6kW Mono 1f', 'Daikin', '20620', '0.3'),
('HeatPump', 'Altherma 3 R 8kW Mono 1f', 'Altherma 3 R 8kW Mono 1f', 'Daikin', '23200', '0.3'),
('HeatPump', 'Altherma 3 R 9kW Mono 1f', 'Altherma 3 R 9kW Mono 1f', 'Daikin', '26360', '0.3'),
('HeatPump', 'Altherma 3 R 9kW Mono 3f', 'Altherma 3 R 9kW Mono 3f', 'Daikin', '27960', '0.3'),
('HeatPump', 'Altherma 3 R 11kW Mono 1f', 'Altherma 3 R 11kW Mono 1f', 'Daikin', '27910', '0.3'),
('HeatPump', 'Altherma 3 R 11kW Mono 3f', 'Altherma 3 R 11kW Mono 3f', 'Daikin', '31590', '0.3'),
('HeatPump', 'Altherma 3 R 14kW Mono 1f', 'Altherma 3 R 14kW Mono 1f', 'Daikin', '33310', '0.3'),
('HeatPump', 'Altherma 3 R 14kW Mono 3f', 'Altherma 3 R 14kW Mono 3f', 'Daikin', '38430', '0.3'),
('HeatPump', 'Altherma 3 R 16kW Mono 1f', 'Altherma 3 R 16kW Mono 1f', 'Daikin', '34420', '0.29'),
('HeatPump', 'Altherma 3 R 16kW Mono 3f', 'Altherma 3 R 16kW Mono 3f', 'Daikin', '41870', '0.3');

INSERT INTO product_heat_pump
(`id`, `heat_pump_type_id`, `outdoor_unit`, `indoor_unit`, `rated_power_kw`, `heater_power_kw`, `scop`, `power_phases`, `warranty_years`, `heating_capacity_negative_20_c`, `heating_capacity_negative_15_c`, `heating_capacity_negative_7_c`, `heating_capacity_negative_2_c`, `heating_capacity_positive_2_c`, `heating_capacity_positive_7_c`, `heating_capacity_positive_12_c`, `heating_capacity_positive_15_c`, `heating_capacity_positive_20_c`, `energy_consumption_negative_20_c`, `energy_consumption_negative_15_c`, `energy_consumption_negative_7_c`, `energy_consumption_negative_2_c`, `energy_consumption_positive_2_c`, `energy_consumption_positive_7_c`, `energy_consumption_positive_12_c`, `energy_consumption_positive_15_c`, energy_consumption_positive_20_c)
VALUES
(29, 2, 'WH-UDZ03KE5', 'WH-SDC0309K3E5', '3', '3', '5.07', 3, 5, '3.2', '3.2', '3.3', '3.24', '3.2', '3.2', '3.2', '3.2', '3.2', '1.49', '1.37', '1.18', '1.01', '0.88', '0.6', '0.6', '0.6', '0.6'),
(30, 2, 'WH-UDZ05KE5', 'WH-SDC0309K3E5', '5', '3', '5.12', 5, 5, '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '2.31', '2.11', '1.79', '1.57', '1.4', '0.98', '0.98', '0.98', '0.98'),
(31, 2, 'WH-UDZ07KE5', 'WH-SDC0309K3E5', '7', '3', '4.9', 7, 5, '5.51', '5.6', '5.75', '6.36', '6.85', '7.0', '7.0', '7.0', '7.0', '2.65', '2.38', '1.95', '1.98', '2.0', '1.44', '1.44', '1.44', '1.44'),
(32, 2, 'WH-UDZ09KE5', 'WH-SDC0309K3E5', '9', '3', '4.44', 9, 5, '8.12', '7.4', '6.25', '6.67', '7.0', '9.0', '9.0', '9.0', '9.0', '3.83', '3.2', '2.2', '2.12', '2.06', '1.98', '1.98', '1.98', '1.98'),
(33, 2, 'WH-UD12HE5', 'WH-SDC12H6E5', '12', '6', '4.82', 12, 5, '7.9', '8.9', '10.0', '10.78', '11.4', '12.0', '12.0', '12.0', '12.0', '2.12', '3.62', '3.66', '3.47', '3.31', '2.53', '0.89', '0.83', '1.66'),
(34, 2, 'WH-UD12HE8', 'WH-SDC12H9E8', '12', '9', '4.82', 12, 5, '7.9', '8.9', '10.0', '10.78', '11.4', '12.0', '12.0', '12.0', '12.0', '2.03', '3.62', '3.66', '3.47', '3.31', '2.53', '1.25', '1.12', '1.66'),
(35, 2, 'WH-UD16HE5', 'WH-SDC16H6E5', '16', '6', '4.82', 16, 5, '9.2', '10.3', '11.4', '12.29', '13.0', '16.0', '16.0', '16.0', '16.0', '3.83', '4.38', '4.43', '4.17', '3.96', '3.74', '1.73', '1.57', '2.69'),
(36, 2, 'WH-UD16HE8', 'WH-SDC16H9E8', '16', '9', '4.82', 16, 5, '9.2', '10.3', '11.4', '12.29', '13.0', '16.0', '16.0', '16.0', '16.0', '3.24', '4.38', '4.43', '4.17', '3.96', '3.74', '1.62', '1.48', '2.69'),
(37, 2, 'WH-UXZ09KE5', 'WH-SXC09K3E5', '9', '3', '4.96', 9, 5, '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '10.28', '11.04', '12.32', '4.5', '3.51', '3.16', '2.8', '2.51', '1.86', '1.81', '1.76', '1.71'),
(38, 2, 'WH-UXZ09KE8', 'WH-SXC09K3E8', '9', '3', '4.96', 9, 5, '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '10.28', '11.04', '12.32', '4.5', '3.51', '3.16', '2.8', '2.51', '1.86', '1.81', '1.76', '1.71'),
(39, 2, 'WH-UXZ12KE5', 'WH-SXC12K6E5', '12', '9', '4.96', 12, 5, '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '10.28', '11.04', '12.32', '6.32', '4.96', '4.41', '3.9', '3.49', '2.53', '2.23', '2.06', '1.76'),
(40, 2, 'WH-UXZ12KE8', 'WH-SXC12K9E8', '12', '9', '4.96', 12, 5, '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '10.28', '11.04', '12.32', '6.32', '4.96', '4.41', '3.9', '3.49', '2.53', '2.23', '2.06', '1.76'),
(41, 2, 'WH-UX16HE8', 'WH-SXC16H9E8', '16', '9', '4.08', 16, 5, '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '8.89', '6.89', '6.42', '5.75', '5.21', '3.18', '2.95', '2.81', '2.58'),
(42, 3, 'WH-UDZ03KE5', 'WH-ADC0309K3E5', '3', '3', '5.07', 3, 5, '3.14', '3.2', '3.3', '3.24', '3.2', '3.2', '3.2', '3.2', '3.2', '1.49', '1.37', '1.18', '1.01', '0.88', '0.6', '0.6', '0.6', '0.6'),
(43, 3, 'WH-UDZ05KE5', 'WH-ADC0309K3E6', '5', '3', '5.12', 5, 5, '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '2.31', '2.11', '1.79', '1.57', '1.4', '0.98', '0.98', '0.98', '0.98'),
(44, 3, 'WH-UDZ07KE5', 'WH-ADC0309K3E7', '7', '3', '4.9', 7, 5, '5.51', '5.6', '5.75', '6.36', '6.85', '7.0', '7.0', '7.0', '7.0', '2.65', '2.38', '1.95', '1.98', '2.0', '1.44', '1.44', '1.44', '1.44'),
(45, 3, 'WH-UDZ09KE5', 'WH-ADC0309K3E8', '9', '3', '4.44', 9, 5, '8.12', '7.4', '6.25', '6.67', '7.0', '9.0', '9.0', '9.0', '9.0', '3.83', '3.2', '2.2', '2.12', '2.06', '1.98', '1.98', '1.98', '1.98'),
(46, 3, 'WH-UD12HE5', 'WH-ADC1216H6E5', '12', '6', '4.82', 12, 5, '7.9', '8.9', '10.0', '10.78', '11.4', '12.0', '12.0', '12.0', '12.0', '3.73', '3.62', '3.66', '3.47', '3.31', '2.53', '1.8', '1.77', '1.66'),
(47, 3, 'WH-UD12HE8', 'WH-ADC0916H9E8', '12', '9', '4.82', 12, 5, '7.9', '8.9', '10.0', '10.78', '11.4', '12.0', '12.0', '12.0', '12.0', '3.73', '3.62', '3.66', '3.47', '3.31', '2.53', '1.8', '1.77', '1.66'),
(48, 3, 'WH-UD16HE5', 'WH-ADC1216H6E5', '16', '6', '4.82', 16, 5, '9.2', '10.3', '11.4', '12.29', '13.0', '16.0', '16.0', '16.0', '16.0', '5.3', '4.38', '4.43', '4.17', '3.96', '3.74', '2.23', '2.06', '2.69'),
(49, 3, 'WH-UD16HE8', 'WH-ADC0916H9E8', '16', '9', '4.82', 16, 5, '9.2', '10.3', '11.4', '12.29', '13.0', '16.0', '16.0', '16.0', '16.0', '7.18', '4.38', '4.43', '4.17', '3.96', '3.74', '2.95', '2.81', '2.69'),
(50, 3, 'WH-UXZ09KE5', 'WH-ADC0912K6E5', '9', '3', '4.96', 9, 5, '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '10.28', '11.04', '12.32', '4.5', '3.51', '3.16', '2.8', '2.51', '1.86', '1.81', '1.76', '1.71'),
(51, 3, 'WH-UXZ09KE8', 'WH-ADC0912K9E8', '9', '3', '4.96', 9, 5, '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '10.28', '11.04', '12.32', '4.5', '3.51', '3.16', '2.8', '2.51', '1.86', '1.81', '1.76', '1.71'),
(52, 3, 'WH-UXZ12KE5', 'WH-ADC0912K6E5', '12', '9', '4.96', 12, 5, '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '10.28', '11.04', '12.32', '6.32', '4.96', '4.41', '3.9', '3.49', '2.53', '2.23', '2.06', '1.76'),
(53, 3, 'WH-UXZ12KE8', 'WH-ADC0912K9E8', '12', '9', '4.96', 12, 5, '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '10.28', '11.04', '12.32', '6.32', '4.96', '4.41', '3.9', '3.49', '2.53', '2.23', '2.06', '1.76'),
(54, 3, 'WH-UX16HE8', 'WH-ADC0916H9E8', '16', '9', '4.08', 16, 5, '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '8.89', '6.89', '6.42', '5.75', '5.21', '3.18', '2.95', '2.81', '2.58'),
(55, 1, 'WH-MDC05J3E5', NULL, '5', '3', '5.12', 5, 5, '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '5.0', '2.31', '2.11', '1.79', '1.57', '1.4', '0.98', '0.98', '0.98', '0.98'),
(56, 1, 'WH-MDC07J3E5', NULL, '7', '3', '4.9', 7, 5, '5.51', '5.6', '5.75', '6.36', '6.85', '7.0', '7.0', '7.0', '7.0', '2.65', '2.38', '1.95', '1.98', '2.0', '1.44', '1.44', '1.44', '1.44'),
(57, 1, 'WH-MDC09J3E5', NULL, '9', '3', '4.9', 9, 5, '8.12', '7.4', '6.25', '6.67', '7.0', '9.0', '9.0', '9.0', '9.0', '3.83', '3.2', '2.2', '2.12', '2.06', '1.98', '1.98', '1.98', '1.98'),
(58, 1, 'WH-MDC12H6E5', NULL, '12', '6', '4.82', 12, 5, '7.9', '8.9', '10.0', '10.78', '11.4', '12.0', '12.0', '12.0', '12.0', '4.35', '3.62', '3.66', '3.47', '3.31', '2.53', '3.34', '3.09', '1.66'),
(59, 1, 'WH-MDC16H6E5', NULL, '16', '6', '4.82', 16, 5, '9.2', '10.3', '11.4', '12.29', '13.0', '16.0', '16.0', '16.0', '16.0', '1.49', '4.38', '4.43', '4.17', '3.96', '3.74', '0.6', '0.6', '2.69'),
(60, 1, 'WH-MXC09J3E5', NULL, '9', '3', '4.96', 9, 5, '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '10.28', '11.04', '12.32', '4.5', '3.51', '3.16', '2.8', '2.51', '1.86', '1.81', '1.76', '1.71'),
(61, 1, 'WH-MXC09J3E8', NULL, '9', '3', '4.96', 9, 5, '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '10.28', '11.04', '12.32', '4.5', '3.51', '3.16', '2.8', '2.51', '1.86', '1.81', '1.76', '1.71'),
(62, 1, 'WH-MXC12J6E5', NULL, '12', '6', '4.96', 12, 5, '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '10.28', '11.04', '12.32', '6.32', '4.96', '4.41', '3.9', '3.49', '2.53', '2.23', '2.06', '1.76'),
(63, 1, 'WH-MXC12J9E8', NULL, '12', '9', '4.96', 12, 5, '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '10.28', '11.04', '12.32', '6.32', '4.96', '4.41', '3.9', '3.49', '2.53', '2.23', '2.06', '1.76'),
(64, 1, 'WH-MXC16J9E8', NULL, '16', '9', '4.46', 16, 5, '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '8.89', '6.89', '6.42', '5.75', '5.21', '3.18', '2.95', '2.81', '2.58'),
(65, 2, 'HU041MR.U20', 'HN0613M.NK5', '4', '3', '4.65', 4, 5, '4.0', '4.0', '4.0', '4.0', '4.0', '4.0', '4.0', '4.0', '4.0', '1.84', '1.65', '1.3', '1.06', '1.1', '0.78', '0.77', '0.69', '0.62'),
(66, 2, 'HU051MR.U44', 'HN091MR.NK5', '5', '3', '4.65', 5, 5, '4.51', '5.12', '5.5', '5.5', '5.5', '5.5', '5.5', '5.5', '5.5', '2.18', '2.23', '2.04', '1.76', '1.57', '1.12', '1.03', '0.91', '0.81'),
(67, 2, 'HU061MR.U20', 'HN0613M.NK5', '6', '3', '4.65', 6, 5, '4.78', '5.52', '6.0', '6.0', '6.0', '6.0', '6.0', '6.0', '6.0', '2.28', '2.35', '2.01', '1.65', '1.7', '1.21', '1.19', '1.06', '0.95'),
(68, 2, 'HU071MR.U44', 'HN091MR.NK5', '7', '3', '4.65', 7, 5, '5.43', '6.0', '7.0', '7.0', '7.0', '7.0', '7.0', '7.0', '7.0', '2.69', '2.67', '2.57', '2.3', '2.06', '1.43', '1.33', '1.18', '1.07'),
(69, 2, 'HU091MR.U44', 'HN091MR.NK5', '9', '3', '4.65', 9, 5, '7.0', '7.8', '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '3.57', '3.51', '3.32', '3.03', '2.69', '1.94', '1.81', '1.64', '1.49'),
(70, 2, 'HU121MRB.U30', 'HN1600MC.NK1', '12', '6', '4.6', 12, 5, '10.0', '11.5', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '4.69', '4.79', '4.0', '3.48', '3.11', '2.38', '2.27', '2.04', '1.83'),
(71, 2, 'HU123MRB.U30', 'HN1600MC.NK1', '12', '6', '4.6', 12, 5, '10.0', '11.5', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '4.69', '4.79', '4.0', '3.48', '3.11', '2.38', '2.27', '2.04', '1.83'),
(72, 2, 'HU141MRB.U30', 'HN1600MC.NK1', '14', '6', '4.57', 14, 5, '11.25', '12.9', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '5.49', '5.61', '4.75', '4.19', '3.76', '2.86', '2.83', '2.45', '2.2'),
(73, 2, 'HU143MRB.U30', 'HN1600MC.NK1', '14', '6', '4.57', 14, 5, '11.25', '12.9', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '5.49', '5.61', '4.75', '4.19', '3.76', '2.86', '2.83', '2.45', '2.2'),
(74, 2, 'HU161MRB.U30', 'HN1600MC.NK1', '16', '6', '4.55', 16, 5, '12.54', '14.39', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '5.75', '6.37', '5.71', '5.02', '4.69', '3.33', '3.15', '2.68', '2.33'),
(75, 2, 'HU163MRB.U30', 'HN1600MC.NK1', '16', '6', '4.55', 16, 5, '12.54', '14.39', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '5.75', '6.37', '5.71', '5.02', '4.69', '3.33', '3.15', '2.68', '2.33'),
(76, 3, 'HU041MR.U20', 'HN0613T.NK0', '4', '3', '4.65', 4, 5, '4.0', '4.0', '4.0', '4.0', '4.0', '4.0', '4.0', '4.0', '4.0', '1.84', '1.65', '1.3', '1.06', '1.1', '0.78', '0.77', '0.69', '0.62'),
(77, 3, 'HU051MR.U44', 'HN0913T.NK0', '5', '3', '4.65', 5, 5, '4.51', '5.12', '5.5', '5.5', '5.5', '5.5', '5.5', '5.5', '5.5', '2.18', '2.23', '2.04', '1.76', '1.57', '1.12', '1.03', '0.91', '0.81'),
(78, 3, 'HU061MR.U20', 'HN0613T.NK0', '6', '3', '4.65', 6, 5, '4.78', '5.52', '6.0', '6.0', '6.0', '6.0', '6.0', '6.0', '6.0', '2.28', '2.35', '2.01', '1.65', '1.7', '1.21', '1.19', '1.06', '0.95'),
(79, 3, 'HU071MR.U44', 'HN0913T.NK0', '7', '3', '4.65', 7, 5, '5.43', '6.0', '7.0', '7.0', '7.0', '7.0', '7.0', '7.0', '7.0', '2.69', '2.67', '2.57', '2.3', '2.06', '1.43', '1.33', '1.18', '1.07'),
(80, 3, 'HU091MR.U44', 'HN0913T.NK0', '9', '3', '4.65', 9, 5, '7.0', '7.8', '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '3.57', '3.51', '3.32', '3.03', '2.69', '1.94', '1.81', '1.64', '1.49'),
(81, 3, 'HU121MRB.U30', 'HN1616Y.NB1', '12', '6', '4.6', 12, 5, '10.0', '11.5', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '4.69', '4.79', '4.0', '3.48', '3.11', '2.38', '2.27', '2.04', '1.83'),
(82, 3, 'HU123MRB.U30', 'HN1616Y.NB1', '12', '6', '4.6', 12, 5, '10.0', '11.5', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '4.69', '4.79', '4.0', '3.48', '3.11', '2.38', '2.27', '2.04', '1.83'),
(83, 3, 'HU141MRB.U30', 'HN1616Y.NB1', '14', '6', '4.57', 14, 5, '11.25', '12.9', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '5.49', '5.61', '4.75', '4.19', '3.76', '2.86', '2.83', '2.45', '2.2'),
(84, 3, 'HU143MRB.U30', 'HN1616Y.NB1', '14', '6', '4.57', 14, 5, '11.25', '12.9', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '5.49', '5.61', '4.75', '4.19', '3.76', '2.86', '2.83', '2.45', '2.2'),
(85, 3, 'HU161MRB.U30', 'HN1616Y.NB1', '16', '6', '4.55', 16, 5, '12.54', '14.39', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '5.75', '6.37', '5.71', '5.02', '4.69', '3.33', '3.15', '2.68', '2.33'),
(86, 3, 'HU163MRB.U30', 'HN1616Y.NB1', '16', '6', '4.55', 16, 5, '12.54', '14.39', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '5.75', '6.37', '5.71', '5.02', '4.69', '3.33', '3.15', '2.68', '2.33'),
(87, 1, 'HM051MR.U44', NULL, '5', '3', '4.46', 5, 5, '5.5', '5.5', '5.5', '5.5', '5.5', '5.5', '5.5', '5.5', '5.5', '2.31', '2.2', '1.72', '1.45', '1.31', '1.71', '1.02', '0.93', '0.86'),
(88, 1, 'HM071MR.U44', NULL, '7', '3', '4.48', 7, 5, '6.43', '7.0', '7.0', '7.0', '7.0', '7.0', '7.0', '7.0', '7.0', '2.75', '2.86', '2.22', '1.85', '1.67', '1.49', '1.3', '1.19', '1.1'),
(89, 1, 'HM091MR.U44', NULL, '9', '3', '4.55', 9, 5, '7.6', '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '3.29', '3.75', '2.9', '2.43', '2.2', '1.96', '1.71', '1.56', '1.44'),
(90, 1, 'HM093MR.U44', NULL, '9', '3', '4.55', 9, 5, '7.6', '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '9.0', '3.29', '3.75', '2.9', '2.43', '2.2', '1.96', '1.71', '1.56', '1.44'),
(91, 1, 'HM121MR.U34', NULL, '12', '6', '4.67', 12, 5, '10.75', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '4.35', '4.71', '3.53', '3.17', '2.86', '2.45', '2.18', '2.0', '1.85'),
(92, 1, 'HM123MR.U34', NULL, '12', '6', '4.67', 12, 5, '10.75', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '12.0', '4.35', '4.71', '3.53', '3.17', '2.86', '2.45', '2.18', '2.0', '1.85'),
(93, 1, 'HM141MR.U34', NULL, '14', '6', '4.62', 14, 5, '12.0', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '4.96', '5.6', '4.19', '3.63', '3.27', '2.92', '2.6', '2.38', '2.19'),
(94, 1, 'HM143MR.U34', NULL, '14', '6', '4.62', 14, 5, '12.0', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '14.0', '4.96', '5.6', '4.19', '3.63', '3.27', '2.92', '2.6', '2.38', '2.19'),
(95, 1, 'HM161MR.U34', NULL, '16', '6', '4.53', 16, 5, '13.25', '14.4', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '5.71', '5.88', '4.89', '5.76', '3.82', '3.4', '3.05', '2.79', '2.53'),
(96, 1, 'HM163MR.U34', NULL, '16', '6', '4.53', 16, 5, '13.25', '14.4', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '16.0', '5.71', '5.88', '4.89', '5.76', '3.82', '3.4', '3.05', '2.79', '2.53'),
(97, 2, 'ERGA04EAV3', 'EHBH04E6V', '4', '6', '4.48', 4, 5, '4.29', '4.71', '5.38', '5.43', '5.46', '6.41', '6.07', '5.72', '5.15', '2.45', '2.24', '1.91', '1.68', '1.49', '1.3', '1.01', '0.86', '0.63'),
(98, 2, 'ERGA06EAV3H', 'EHBH08E6V', '6', '6', '4.47', 6, 5, '5.13', '5.56', '6.25', '6.2', '6.17', '7.74', '7.52', '7.25', '6.79', '2.82', '2.6', '2.25', '1.97', '1.74', '1.63', '1.27', '1.13', '0.89'),
(99, 2, 'ERGA08EAV3H7', 'EHBH08E6V', '8', '6', '4.56', 8, 5, '6.14', '6.58', '7.28', '7.24', '7.22', '9.37', '9.21', '8.82', '8.16', '3.43', '3.16', '2.73', '2.41', '2.16', '2.08', '1.71', '1.5', '1.14'),
(100, 2, 'ERLA11DV3', 'EBBH11D6V', '11', '6', '4.63', 11, 5, '6.82', '7.67', '9.02', '9.43', '9.76', '12.44', '10.16', '10.27', '10.45', '3.05', '3.04', '3.02', '2.83', '2.68', '2.57', '1.74', '1.56', '1.27'),
(101, 2, 'ERLA11DW1', 'EBBH11D9W', '11', '9', '4.63', 11, 5, '6.82', '7.67', '9.02', '9.43', '9.76', '12.44', '10.16', '10.27', '10.45', '3.05', '3.04', '3.02', '2.83', '2.68', '2.57', '1.74', '1.56', '1.27'),
(102, 2, 'ERLA14DV3', 'EBBH16D6V', '14', '6', '4.6', 14, 5, '7.25', '8.04', '9.29', '10.21', '10.95', '13.38', '11.39', '12.18', '13.5', '3.25', '3.23', '3.19', '3.15', '3.12', '2.83', '2.0', '1.94', '1.85'),
(103, 2, 'ERLA14DW1', 'EBBH16D9W', '14', '9', '4.6', 14, 5, '7.25', '8.04', '9.29', '10.21', '10.95', '13.38', '11.39', '12.18', '13.5', '3.25', '3.23', '3.19', '3.15', '3.12', '2.83', '2.0', '1.94', '1.85'),
(104, 2, 'ERLA16DV37', 'EBBH16D6V', '16', '6', '4.61', 16, 5, '9.16', '9.81', '10.84', '11.44', '11.92', '15.96', '14.47', '14.37', '14.19', '4.28', '4.19', '4.06', '3.81', '3.61', '3.45', '2.53', '2.51', '2.46'),
(105, 2, 'ERLA16DW17', 'EBBH16D9W', '16', '9', '4.61', 16, 5, '9.16', '9.81', '10.84', '11.44', '11.92', '15.96', '14.47', '14.37', '14.19', '4.28', '4.19', '4.06', '3.81', '3.61', '3.45', '2.53', '2.51', '2.46'),
(106, 3, 'ERGA04EAV3', 'EHVH04S18E6V', '4', '6', '4.48', 4, 5, '4.29', '4.71', '5.38', '5.43', '5.46', '6.41', '6.07', '5.72', '5.15', '2.45', '2.24', '1.91', '1.68', '1.49', '1.3', '1.01', '0.86', '0.63'),
(107, 3, 'ERGA06EAV3H', 'EHVH08S180E6V', '6', '6', '4.47', 6, 5, '5.13', '5.56', '6.25', '6.2', '6.17', '7.74', '7.52', '7.25', '6.79', '2.82', '2.6', '2.25', '1.97', '1.74', '1.63', '1.27', '1.13', '0.89'),
(108, 3, 'ERGA08EAV3H7', 'EHVH08S180E6V', '8', '6', '4.56', 8, 5, '6.14', '6.58', '7.28', '7.24', '7.22', '9.37', '9.21', '8.82', '8.16', '3.43', '3.16', '2.73', '2.41', '2.16', '2.08', '1.71', '1.5', '1.14'),
(109, 3, 'ERLA11DV3', 'EBVH11S18D9W', '11', '6', '4.63', 11, 5, '6.82', '7.67', '9.02', '9.43', '9.76', '12.44', '10.16', '10.27', '10.45', '3.05', '3.04', '3.02', '2.83', '2.68', '2.57', '1.74', '1.56', '1.27'),
(110, 3, 'ERLA11DW1', 'EBVH11S18D9W', '11', '9', '4.63', 11, 5, '6.82', '7.67', '9.02', '9.43', '9.76', '12.44', '10.16', '10.27', '10.45', '3.05', '3.04', '3.02', '2.83', '2.68', '2.57', '1.74', '1.56', '1.27'),
(111, 3, 'ERLA14DV3', 'EBVH16S18D6V', '14', '6', '4.6', 14, 5, '7.25', '8.04', '9.29', '10.21', '10.95', '13.38', '11.39', '12.18', '13.5', '3.25', '3.23', '3.19', '3.15', '3.12', '2.83', '2.0', '1.94', '1.85'),
(112, 3, 'ERLA14DW1', 'EBVH16S18D9W', '14', '9', '4.6', 14, 5, '7.25', '8.04', '9.29', '10.21', '10.95', '13.38', '11.39', '12.18', '13.5', '3.25', '3.23', '3.19', '3.15', '3.12', '2.83', '2.0', '1.94', '1.85'),
(113, 3, 'ERLA16DV3', 'EBVH16S18D6V', '16', '6', '4.61', 16, 5, '9.16', '9.81', '10.84', '11.44', '11.92', '15.96', '14.47', '14.37', '14.19', '4.28', '4.19', '4.06', '3.81', '3.61', '3.45', '2.53', '2.51', '2.46'),
(114, 3, 'ERLA16DW1', 'EBVH16S18D9W', '16', '9', '4.61', 16, 5, '9.16', '9.81', '10.84', '11.44', '11.92', '15.96', '14.47', '14.37', '14.19', '4.28', '4.19', '4.06', '3.81', '3.61', '3.45', '2.53', '2.51', '2.46'),
(115, 1, 'EDLA04E3V3', NULL, '4', '3', '4.48', 4, 5, '4.29', '4.71', '5.38', '5.43', '5.46', '6.41', '6.07', '5.72', '5.15', '2.45', '2.24', '1.91', '1.68', '1.49', '1.3', '1.01', '0.86', '0.63'),
(116, 1, 'EDLA06E3V3', NULL, '6', '3', '4.47', 6, 5, '5.13', '5.56', '6.25', '6.2', '6.17', '7.74', '7.52', '7.25', '6.79', '2.82', '2.6', '2.25', '1.97', '1.74', '1.63', '1.27', '1.13', '0.89'),
(117, 1, 'EDLA08E3V3', NULL, '8', '3', '4.56', 8, 5, '6.14', '6.58', '7.28', '7.24', '7.22', '9.37', '9.21', '8.82', '8.16', '3.43', '3.16', '2.73', '2.41', '2.16', '2.08', '1.71', '1.5', '1.14'),
(118, 1, 'EDLA09D3V3', NULL, '9', '3', '4.72', 9, 5, '6.57', '7.08', '7.89', '8.91', '8.98', '10.42', '13.55', '15.09', '17.65', '3.2', '3.31', '3.22', '3.27', '2.97', '2.17', '3.18', '3.04', '2.81'),
(119, 1, 'EDLA09D3W1', NULL, '9', '3', '4.72', 9, 5, '6.57', '7.08', '7.89', '8.91', '8.98', '10.42', '13.55', '15.09', '17.65', '3.2', '3.31', '3.22', '3.27', '2.97', '2.17', '3.18', '3.04', '2.81'),
(120, 1, 'EDLA11D3V3', NULL, '11', '3', '4.62', 11, 5, '8.45', '8.58', '9.1', '10.08', '10.08', '12.31', '14.06', '15.6', '18.18', '4.21', '4.19', '3.72', '3.99', '3.31', '2.57', '3.11', '2.98', '2.76'),
(121, 1, 'EDLA11D3W1', NULL, '11', '3', '4.62', 11, 5, '8.45', '8.58', '9.1', '10.08', '10.08', '12.31', '14.06', '15.6', '18.18', '4.21', '4.19', '3.72', '3.99', '3.31', '2.57', '3.11', '2.98', '2.76'),
(122, 1, 'EDLA14D3V3', NULL, '14', '3', '4.64', 14, 5, '8.78', '9.53', '10.73', '11.49', '11.1', '13.69', '15.09', '16.25', '18.18', '4.44', '4.41', '4.35', '4.19', '3.6', '2.95', '2.97', '2.89', '2.76'),
(123, 1, 'EDLA14D3W1', NULL, '14', '3', '4.64', 14, 5, '8.78', '9.53', '10.73', '11.49', '11.1', '13.69', '15.09', '16.25', '18.18', '4.44', '4.41', '4.35', '4.19', '3.6', '2.95', '2.97', '2.89', '2.76'),
(124, 1, 'EDLA16D3V37', NULL, '16', '3', '4.62', 16, 5, '9.07', '9.87', '11.15', '12.02', '12.35', '15.96', '17.08', '17.73', '18.82', '4.68', '4.59', '4.44', '4.25', '3.94', '3.52', '3.24', '3.04', '2.7'),
(125, 1, 'EDLA16D3W17', NULL, '16', '3', '4.62', 16, 5, '9.07', '9.87', '11.15', '12.02', '12.35', '15.96', '17.08', '17.73', '18.82', '4.68', '4.59', '4.44', '4.25', '3.94', '3.52', '3.24', '3.04', '2.7');

