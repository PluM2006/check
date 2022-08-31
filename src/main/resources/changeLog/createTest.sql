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

INSERT INTO product(name, price, count, sale)
VALUES ('Картофель', 7.31, 11, true),
       ('Морковь',25.31,20,false),
       ('Лук',28.65,18,true),
       ('Чеснок',26.19,3,false),
       ('Петрушка',63.03,2,false),
       ('Укроп',40.14,8,false),
       ('Бананы',48.57,15,false),
       ('Лимон',35.15,11,true),
       ('Масло сливочное',45.79,10,false),
       ('Кефир',1.00,18,true),
       ('Молоко детское',81.02,18,false),
       ('Сметана',-1.01,2,false),
       ('Творог',68.82,11,true),
       ('Сыр',83.74,5,false),
       ('Яйца',12.51,9,false),
       ('Масло растительное',56.87,18,false),
       ('Соевый соус',35.60,2,false),
       ('Уксус обычный',48.52,14,true),
       ('Баллончик со сливками',9.95,12,true),
       ('Кошачий корм',26.11,2,false),
       ('Гречка',53.79,20,false),
       ('Перловка',87.02,20,true),
       ('Рис',24.81,17,true),
       ('Овсяные хлопья',66.37,6,false),
       ('Ваниль',29.66,7,true),
       ('Корица',88.64,11,false),
       ('Карри',31.03,12,false),
       ('Черный перец',16.36,12,true),
       ('Красный перец',38.39,5,false),
       ('Соль',66.62,8,false),
       ('Паприка',37.77,4,true),
       ('Куркума',20.1,100,false),
       ('Лавровый лист',57.60,16,false),
       ('Чай. кофе',173.04,14,false),
       ('Кофе растворимый',91.62,3,false),
       ('Чай черный',16.95,6,false),
       ('Чай зеленый',20.59,2,false),
       ('Чай мятный',-15.66,13,false),
       ('Apple',5.25,5,true),
       ('Просо',73.04,3,false),
       ('Яйца',91.62,4,false),
       ('Огурец!',1.00,5,false),
       ('Картофель2',15.55,10,false),
       ('Холодильник',15.55,5,false),
       ('Чай красный',20.59,2,false),
       ('Кофе',16.36,6,false),
       ('ЯблокИ',38.39,2,true),
       ('Хлеб 15',66.62,13,true),
       ('Конфеты хрустяшки обояшки самые вкусные конфеты',550.5,12,true),
       ('Dваываыва ываsыwssssd sds sdss',100.01,45,false),
       ('apple',-5,21,false),
       ('Apple',1.12,2,false),
       ('MyApple',1.12,2,false),
       ('Apple',2.001,2,false),
       ('Apple',1.12,50,false),
       ('Кофе Молотый',1.255,2,false),
       ('Апельсин',1000.2,2.5,true),
       ('Лимон',50.11,2.15,false),
       ('Грейпфрут',1,25,false),
       ('Lимон',66.37,6,false),
       ('Sоевый sоус',66.37,6,false);

INSERT INTO card (numbercard,discount)
VALUES ('1111 1111 1111 1111',10),
       ('2222 2222 2222 2222',5),
       ('3333 3333 3333 3333',7);

INSERT INTO cashier ("name", number)
VALUES ('Luke Skywalker', '007'),
       ('Mickey Rourke', '001');

INSERT INTO shop ("name", adress)
VALUES ('Krama №646', '3-я ул. Строителей, 25')