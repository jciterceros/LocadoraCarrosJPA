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