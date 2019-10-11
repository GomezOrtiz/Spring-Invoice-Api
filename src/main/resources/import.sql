/* Populate table clients */

INSERT INTO clients (id, name, surname, email, created_at) VALUES (1, 'David', 'Gómez', 'dagor@gmail.com', '2019-08-06');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (2, 'Lucía', 'Astray', 'lulas@gmail.com', '2019-08-04');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (3, 'Alfonso', 'Cerezuela', 'fons@gmail.com', '2019-07-15');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (4, 'Enrique', 'Álvarez de Toledo', 'henry@gmail.com', '2019-07-15');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (5, 'José Luis', 'Avilés', 'peplu@gmail.com', '2019-07-15');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (6, 'Carlos', 'Martínez', 'carlos@gmail.com', '2015-05-04');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (7, 'John', 'Doe', 'john.doe@gmail.com', '2017-08-02');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (8, 'Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (9, 'Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (10, 'Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (11, 'Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (12, 'Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (13, 'Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (14, 'John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (15, 'James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (16, 'Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (17, 'Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (18, 'John', 'Roe', 'john.roe@gmail.com', '2017-08-13');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (19, 'Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14');
INSERT INTO clients (id, name, surname, email, created_at) VALUES (20, 'Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15');

/* Populate table products */

INSERT INTO products (id, name, price, discontinued, created_at) VALUES (1, 'Panasonic Pantalla LCD', 259990, false, NOW());
INSERT INTO products (id, name, price, discontinued, created_at) VALUES (2, 'Sony Camara digital DSC-W320B', 123490, false, NOW());
INSERT INTO products (id, name, price, discontinued, created_at) VALUES (3, 'Apple iPod shuffle', 1499990, true, NOW());
INSERT INTO products (id, name, price, discontinued, created_at) VALUES (4, 'Sony Notebook Z110', 37990, false, NOW());
INSERT INTO products (id, name, price, discontinued, created_at) VALUES (5, 'Hewlett Packard Multifuncional F2280', 69990, false, NOW());
INSERT INTO products (id, name, price, discontinued, created_at) VALUES (6, 'Bianchi Bicicleta Aro 26', 69990, false, NOW());
INSERT INTO products (id, name, price, discontinued, created_at) VALUES (7, 'Mica Comoda 5 Cajones', 299990, false, NOW());

/* Create some invoices */

INSERT INTO invoices (id, description, details, client_id, total, created_at) VALUES (1, 'Factura equipos de oficina', null, 1, 10000, NOW());
INSERT INTO invoice_items (id, amount, invoice_id, product_id) VALUES (1, 1, 1, 1);
INSERT INTO invoice_items (id, amount, invoice_id, product_id) VALUES (2, 2, 1, 4);
INSERT INTO invoice_items (id, amount, invoice_id, product_id) VALUES (3, 1, 1, 5);
INSERT INTO invoice_items (id, amount, invoice_id, product_id) VALUES (4, 1, 1, 7);

INSERT INTO invoices (id, description, details, client_id, total, created_at) VALUES (2, 'Factura Bicicleta', 'Alguna nota importante!', 1, 5000, NOW());
INSERT INTO invoice_items (id, amount, invoice_id, product_id) VALUES (5, 3, 2, 6);