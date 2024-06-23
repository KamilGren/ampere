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

