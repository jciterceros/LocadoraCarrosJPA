--- Inserindo 10 Fabricantes de automoveis

INSERT INTO tb_fabricante (nome) VALUES ('Toyota');
INSERT INTO tb_fabricante (nome) VALUES ('Ford');
INSERT INTO tb_fabricante (nome) VALUES ('Honda');
INSERT INTO tb_fabricante (nome) VALUES ('Chevrolet');
INSERT INTO tb_fabricante (nome) VALUES ('Volkswagen');
INSERT INTO tb_fabricante (nome) VALUES ('Nissan');
INSERT INTO tb_fabricante (nome) VALUES ('BMW');
INSERT INTO tb_fabricante (nome) VALUES ('Mercedes-Benz');
INSERT INTO tb_fabricante (nome) VALUES ('Audi');
INSERT INTO tb_fabricante (nome) VALUES ('Hyundai');


-- 5 Inserts de modelos para a fabricante 'Toyota'
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Corolla', 1);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Camry', 1);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Rav4', 1);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Highlander', 1);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Prius', 1);

-- 5 Inserts de modelos para a fabricante 'Ford'
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Mustang', 2);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('F-150', 2);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Explorer', 2);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Escape', 2);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Focus', 2);

-- 5 Inserts de modelos para a fabricante 'Honda'
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Civic', 3);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Accord', 3);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('CR-V', 3);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Pilot', 3);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Fit', 3);

-- 5 Inserts de modelos para a fabricante 'Chevrolet'
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Camaro', 4);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Silverado', 4);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Malibu', 4);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Equinox', 4);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Traverse', 4);

-- 5 Inserts de modelos para a fabricante 'Volkswagen'
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Golf', 5);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Jetta', 5);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Tiguan', 5);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Atlas', 5);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Passat', 5);

-- 5 Inserts de modelos para a fabricante 'Nissan'
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Altima', 6);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Rogue', 6);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Titan', 6);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Murano', 6);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('370Z', 6);

-- 5 Inserts de modelos para a fabricante 'BMW'
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('X5', 7);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('3 Series', 7);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('7 Series', 7);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('i8', 7);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('M5', 7);

-- 5 Inserts de modelos para a fabricante 'Mercedes-Benz'
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('C-Class', 8);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('E-Class', 8);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('S-Class', 8);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('GLC', 8);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('GLE', 8);

-- 5 Inserts de modelos para a fabricante 'Audi'
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('A4', 9);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Q5', 9);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('A6', 9);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Q7', 9);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('R8', 9);

-- 5 Inserts de modelos para a fabricante 'Hyundai'
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Elantra', 10);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Tucson', 10);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Santa Fe', 10);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Kona', 10);
INSERT INTO tb_modelo (nome, id_fabricante) VALUES ('Palisade', 10);



-- Carros para a fabricante 'Toyota'
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (1, 1, 'ABC1234', 'BRANCO', true, 2000, 2003);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (1, 2, 'DEF5678', 'PRETO', true, 2500, 2008);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (1, 3, 'GHI9012', 'PRATA', true, 1800, 2015);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (1, 4, 'JKL3456', 'AZUL', true, 2700, 2005);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (1, 5, 'MNO7890', 'VERMELHO', true, 2200, 2020);

-- Carros para a fabricante 'Ford'
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (2, 6, 'PQR1234', 'BRANCO', true, 2300, 2011);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (2, 7, 'STU5678', 'PRETO', true, 2900, 2007);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (2, 8, 'VWX9012', 'PRATA', true, 1700, 2019);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (2, 9, 'YZA3456', 'AZUL', true, 2500, 2017);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (2, 10, 'BCD7890', 'VERMELHO', true, 2800, 2009);

-- Carros para a fabricante 'Honda'
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (3, 11, 'EFG1234', 'BRANCO', true, 2400, 2004);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (3, 12, 'HIJ5678', 'PRETO', true, 2600, 2013);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (3, 13, 'KLM9012', 'PRATA', true, 2100, 2010);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (3, 14, 'NOP3456', 'AZUL', true, 2900, 2002);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (3, 15, 'QRS7890', 'VERMELHO', true, 2200, 2006);

-- Carros para a fabricante 'Chevrolet'
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (4, 16, 'TUV1234', 'BRANCO', true, 2200, 2018);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (4, 17, 'WXY5678', 'PRETO', true, 2400, 2005);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (4, 18, 'ZAB9012', 'PRATA', true, 2700, 2014);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (4, 19, 'CDE3456', 'AZUL', true, 2300, 2009);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (4, 20, 'FGH7890', 'VERMELHO', true, 1900, 2016);

-- Carros para a fabricante 'Volkswagen'
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (5, 21, 'IJK1234', 'BRANCO', true, 2800, 2012);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (5, 22, 'LMN5678', 'PRETO', true, 2600, 2008);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (5, 23, 'OPQ9012', 'PRATA', true, 2300, 2019);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (5, 24, 'RST3456', 'AZUL', true, 2900, 2003);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (5, 25, 'UVW7890', 'VERMELHO', true, 2100, 2011);

-- Carros para a fabricante 'Nissan'
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (6, 26, 'XYZ1234', 'BRANCO', true, 2100, 2010);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (6, 27, 'ABC5678', 'PRETO', true, 2700, 2013);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (6, 28, 'DEF9012', 'PRATA', true, 2400, 2007);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (6, 29, 'GHI3456', 'AZUL', true, 2200, 2018);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (6, 30, 'JKL7890', 'VERMELHO', true, 2900, 2005);

-- Carros para a fabricante 'BMW'
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (7, 31, 'MNO1234', 'BRANCO', true, 2500, 2016);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (7, 32, 'PQR5678', 'PRETO', true, 2200, 2009);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (7, 33, 'STU9012', 'PRATA', true, 2800, 2012);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (7, 34, 'VWX3456', 'AZUL', true, 1900, 2004);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (7, 35, 'YZA7890', 'VERMELHO', true, 2600, 2015);

-- Carros para a fabricante 'Mercedes-Benz'
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (8, 36, 'BCD1234', 'BRANCO', true, 2300, 2019);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (8, 37, 'EFG5678', 'PRETO', true, 2400, 2011);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (8, 38, 'HIJ9012', 'PRATA', true, 2700, 2014);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (8, 39, 'KLM3456', 'AZUL', true, 2100, 2008);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (8, 40, 'NOP7890', 'VERMELHO', true, 2800, 2017);

-- Carros para a fabricante 'Audi'
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (9, 41, 'QRS1234', 'BRANCO', true, 2900, 2010);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (9, 42, 'TUV5678', 'PRETO', true, 2100, 2016);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (9, 43, 'IJK9012', 'PRATA', true, 2600, 2004);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (9, 44, 'LMN3456', 'AZUL', true, 1800, 2019);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (9, 45, 'OPQ7890', 'VERMELHO', true, 2300, 2012);

-- Carros para a fabricante 'Hyundai'
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (10, 46, 'RST1234', 'BRANCO', true, 2700, 2008);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (10, 47, 'UVW5678', 'PRETO', true, 1800, 2014);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (10, 48, 'XYZ9012', 'PRATA', true, 2900, 2005);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (10, 49, 'ABC3456', 'AZUL', true, 2200, 2017);
INSERT INTO tb_carro (id_fabricante, id_modelo, placa, cor, disponivel, valor_locacao, ano) VALUES (10, 50, 'DEF7890', 'VERMELHO', true, 2500, 2012);


