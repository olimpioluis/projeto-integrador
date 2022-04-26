-- CREATE DATABASE IF NOT EXISTS meli_fresh;
-- USE meli_fresh;

/* Persons */
Insert into person (cpf, email, genre,name) values ('001.002.003-01','01@meli.com.br', 'M', 'Arthur');
Insert into person (cpf, email, genre,name) values ('002.002.002-02','02@meli.com.br', 'M', 'Gabriel');
Insert into person (cpf, email, genre,name) values ('003.003.003-03','03@meli.com.br', 'F', 'Maria');
Insert into person (cpf, email, genre,name) values ('003.003.003-03','03@meli.com.br', 'F', 'Romaria');

/* Sellers */
INSERT INTO seller (person_id) VALUES
((SELECT id from person WHERE name='Maria'));

INSERT INTO seller (person_id) VALUES
((SELECT id from person WHERE name='Romaria'));


/* WareHouse */
INSERT INTO warehouse (name) VALUES ('warehouse-sp');
INSERT INTO warehouse (name) VALUES ('warehouse-ba');

/* StockManagers */

INSERT INTO stock_manager (person_id ,warehouse_id ) VALUES
((SELECT id from person WHERE name='Arthur'),
 (SELECT id from warehouse WHERE name='warehouse-sp'));

INSERT INTO stock_manager (person_id ,warehouse_id ) VALUES
((SELECT id from person WHERE name='Gabriel'),
 (SELECT id from warehouse WHERE name='warehouse-sp'));

/* SECTIONS */

INSERT INTO section (category,name,size,current_size,warehouse_id) VALUES
(('FROZEN'),
 ('FROZEN'),
 (100),
 (100),
 (SELECT id from warehouse WHERE name='warehouse-sp'));

INSERT INTO section (category,name,size,current_size,warehouse_id) VALUES
(('REFRIGERATED'),
 ('REFRIGERATED'),
 (100),
 (100),
 (SELECT id from warehouse WHERE name='warehouse-sp'));

INSERT INTO section (category,name,size,current_size,warehouse_id) VALUES
(('FRESH'),
 ('FRESH'),
 (300),
 (300),
 (SELECT id from warehouse WHERE name='warehouse-sp'));

INSERT INTO section (category,name,size,current_size,warehouse_id) VALUES
(('FROZEN'),
 ('FROZEN'),
 (100),
 (100),
 (SELECT id from warehouse WHERE name='warehouse-ba'));

INSERT INTO section (category,name,size,current_size,warehouse_id) VALUES
(('REFRIGERATED'),
 ('REFRIGERATED'),
 (100),
 (100),
 (SELECT id from warehouse WHERE name='warehouse-ba'));

INSERT INTO section (category,name,size,current_size,warehouse_id) VALUES
(('FRESH'),
 ('FRESH'),
 (300),
 (300),
 (SELECT id from warehouse WHERE name='warehouse-ba'));

/* Products */
INSERT INTO product ( height, name, price, width) VALUES
((6.0),
 ('Shampoo'),
 (17.5),
 (5.0));

INSERT INTO product ( height, name, price, width) VALUES
((6.0),
 ('Condicionador'),
 (17.5),
 (5.0));

INSERT INTO product ( height, name, price, width) VALUES
((3.0),
 ('Creme'),
 (23.5),
 (5.0));

/* advertisement */
INSERT INTO advertisement ( price, title, product_id, seller_id) VALUES
((30.0),
 ('Shampoo mais top'),
 (SELECT id from product where name='Shampoo'),
 (SELECT id from  seller where person_id IN ( SELECT id FROM person where name='Romaria')));



INSERT INTO advertisement ( price, title, product_id, seller_id) VALUES
((30.0),
 ('Condicionador mais top'),
 (SELECT id from product where name='Condicionador'),
 (SELECT id from  seller where person_id IN ( SELECT id FROM person where name='Maria')));

INSERT INTO advertisement ( price, title, product_id, seller_id) VALUES
((30.0),
 ('Shampoo mais top'),
 (SELECT id from product where name='Shampoo'),
 (SELECT id from  seller where person_id IN ( SELECT id FROM person where name='Maria')));