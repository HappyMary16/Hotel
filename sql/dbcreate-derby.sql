
DROP TABLE order_room;
DROP TABLE rooms;
DROP TABLE orders;
DROP TABLE categories;
DROP TABLE users;
DROP TABLE roles;
DROP TABLE order_statuses;
DROP TABLE application_statuses;
DROP TABLE applications;

CREATE TABLE roles(

	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'client');

CREATE TABLE users(

	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,

	login VARCHAR(10) NOT NULL UNIQUE,
	
-- not null string columns	
	password VARCHAR(10) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	locale_name VARCHAR(20),
	
-- this declaration contains the foreign key constraint	
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
	role_id INTEGER NOT NULL REFERENCES roles(id) 

-- removing a row with some ID from roles table implies removing 
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in 
-- users table with ROLES_ID=ID
		ON DELETE CASCADE 

-- the same as previous but updating is used insted deleting
		ON UPDATE RESTRICT
);

-- id = 1
INSERT INTO users VALUES(DEFAULT, 'admin', 'admin', 'Ivan', 'Ivanov', NULL, 0);
-- id = 2
INSERT INTO users VALUES(DEFAULT, 'client', 'client', 'Petr', 'Petrov', NULL, 1);
-- id = 3
INSERT INTO users VALUES(DEFAULT, 'петров', 'петров', 'Иван', 'Петров', NULL, 1);

CREATE TABLE rooms(
  id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  number_places INTEGER NOT NULL,
  price INTEGER NOT NULL,
  category_id INTEGER NOT NULL REFERENCES categories(id)
);

INSERT INTO rooms VALUES(0, 1, 1, 1);
-- горячие блюда
INSERT INTO rooms VALUES(DEFAULT, 11, 210, 1); -- 1 (order id)
INSERT INTO rooms VALUES(DEFAULT, 12, 210, 1); -- 2
INSERT INTO rooms VALUES(DEFAULT, 13, 250, 1); -- 3
-- напитки
INSERT INTO rooms VALUES(DEFAULT, 21, 70, 4); -- 4
-- закуски
INSERT INTO rooms VALUES(DEFAULT, 31, 250, 2); -- 7
INSERT INTO rooms VALUES(DEFAULT, 41, 200, 2); -- 8
-- десерт
INSERT INTO rooms VALUES(DEFAULT, 42, 160, 3); -- 9

-- --------------------------------------------------------------
-- CATEGORIES
-- categories names of menu
-- --------------------------------------------------------------
CREATE TABLE categories(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO categories VALUES(0, 'Standard');
INSERT INTO categories VALUES(1, 'Apartment');
INSERT INTO categories VALUES(2, 'Business');
INSERT INTO categories VALUES(3, 'Suite');

CREATE TABLE order_statuses(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO order_statuses VALUES(0, 'Opened');
INSERT INTO order_statuses VALUES(1, 'Confirmed');
INSERT INTO order_statuses VALUES(2, 'Paid');
INSERT INTO order_statuses VALUES(3, 'Closed');

-- --------------------------------------------------------------
-- ORDERS
-- --------------------------------------------------------------
CREATE TABLE orders(
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	bill INTEGER NOT NULL DEFAULT 0,
	user_id INTEGER NOT NULL REFERENCES users(id),
	status_id INTEGER NOT NULL REFERENCES order_statuses(id),
	set_bill DATE,
	start_order DATE NOT NULL,
	end_order DATE NOT NULL
);

-- --------------------------------------------------------------
-- ORDERS_ROOM
-- relation between order and menu
-- each row of this table represents one menu item
-- --------------------------------------------------------------
CREATE TABLE order_room(
	order_id INTEGER NOT NULL REFERENCES orders(id),
	room_id INTEGER NOT NULL REFERENCES rooms(id)
);

INSERT INTO order_room VALUES(1, 1);
INSERT INTO order_room VALUES(1, 7);
INSERT INTO order_room VALUES(1, 5);

INSERT INTO order_room VALUES(2, 1);
INSERT INTO order_room VALUES(2, 7);

CREATE TABLE application_statuses(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO application_statuses VALUES(0, 'Opened');
INSERT INTO application_statuses VALUES(1, 'Closed');

CREATE TABLE applications(
	id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INTEGER NOT NULL REFERENCES users(id),
  number_places INTEGER NOT NULL,
  category_id INTEGER NOT NULL REFERENCES categories(id),
	status_id INTEGER NOT NULL REFERENCES application_statuses(id),
	start_order DATE NOT NULL,
	end_order DATE NOT NULL
);
-- --------------------------------------------------------------
-- test database:
-- --------------------------------------------------------------
SELECT * FROM order_room;
SELECT * FROM rooms;
SELECT * FROM orders;
SELECT * FROM categories;
SELECT * FROM users;
SELECT * FROM roles;
SELECT * FROM order_statuses;
SELECT * FROM application_statuses;
SELECT * FROM applications;