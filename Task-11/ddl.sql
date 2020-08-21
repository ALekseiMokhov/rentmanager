DROP SCHEMA IF EXISTS senla;

CREATE SCHEMA senla;

USE senla;

CREATE TABLE place (
id UUID NOT NULL PRIMARY KEY,
calendar ARRAY NOT NULL);

CREATE TABLE master(
id UUID NOT NULL PRIMARY KEY,
calendar ARRAY NOT NULL,
fullname VARCHAR(255) NOT NULL,
dailypayment DOUBLE NOT NULL,
speciality VARCHAR(255) NOT NULL);

CREATE TABLE orders(
id UUID NOT NULL PRIMARY KEY,
date_of_booking VARCHAR(255) NOT NULL,
beginning_date VARCHAR(255),
finish_date VARCHAR(255),
order_status VARCHAR(255) NOT NULL,
id_place UUID NOT NULL,
FOREIGN KEY(id_place) REFERENCES place(id));


CREATE TABLE orders_masters(
id_orders UUID NOT NULL ,
id_masters UUID NOT NULL ,
FOREIGN KEY(id_orders) REFERENCES orders(id),
FOREIGN KEY(id_masters) REFERENCES master(id))