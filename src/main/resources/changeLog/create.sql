CREATE TABLE product
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(100),
    price NUMERIC(10,2),
    count INTEGER,
    sale BOOLEAN
);
CREATE TABLE card (
                      id SERIAL PRIMARY KEY NOT NULL,
                      numbercard VARCHAR(25),
                      discount NUMERIC(10,2)
);
CREATE TABLE cashier
(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50),
    number VARCHAR(10)

);
CREATE TABLE shop (
                      id SERIAL PRIMARY KEY NOT NULL,
                      name VARCHAR(30),
                      adress VARCHAR(100)
);
