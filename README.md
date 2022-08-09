# CheckRunner

Программа формирует чек магазина.

Если в чеке больше пяти акционных товаров, на них делается скидка 10%, так же если предъявлена карта предоставляется
скидка на весь чек, без учета акционных товаров на которые уже предоставлена скидка 10%

Примеры URL запросов

**Получить чек (GET)**

***id*** - id продукта

***value*** - количество

***card*** - дисконтная карта

```
http://localhost:8080/check?id=1&value=5&id=2&value=6&card=2
http://localhost:8080/check?id=1&value=5&id=2&value=6
```

***Продукты***

**Продукты по id (GET)**

```
http://localhost:8080/products?id=25
```

**Получить все продукты (GET)**

*offset - смещение*

*pagesize - количество на странице (если не указывать по умолчанию 20)*

```
http://localhost:8080/products
http://localhost:8080/products?pagesize=5&offset=3
http://localhost:8080/products?offset=3
```

**Добавить продукты (POST)**

```
http://localhost:8080/products
{
    "name": "Жареная курица",
    "price": "52.69",
    "count": "20",
    "sale": "false"
}
```

**Удалить продукт по id (DELETE)**

```
http://localhost:8080/products?id=25
```

**Обновить продукт (PUT)**

```
http://localhost:8080/products
{
    "id": 1,
    "name": "Бульба",
    "price": 20.00,
    "count": 10,
    "sale": false
}
```

***Дисконтная карта***

**Карта по id (GET)**

```
http://localhost:8080/cards?id=1
```

**Получить все карты (GET)**

*offset - смещение*

*pagesize - количество на странице (если не указывать по умолчанию 20)*

```
http://localhost:8080/cards
http://localhost:8080/cards?pagesize=5&offset=3
http://localhost:8080/cards?offset=3
```

**Добавить карту (POST)**

```
http://localhost:8080/cards
{
    "numberCard": "6666-5555-5555-5555",
    "discount": "10"
}
```

**Удалить карту по id (DELETE)**

```
http://localhost:8080/cards?id=3
```

**Обновить карту (PUT)**

```
http://localhost:8080/cards
{
    "id": "1",
    "numberCard": "6666-5555-5555-5555",
    "discount": "10"
}
```




