# CheckRunner
Программа формирует чек магазина.

Если в чеке больше пяти акционных товаров, на них делается скидка 10%, так же если предъявлена карта предоставляется скидка на весь чек, без учета акционных товаров на которые уже предоставлена скидка 10%

## Программа запускается java CheckRunner <набор параметров>
Например:  *java CheckRunner 3-1 2-5 5-1 card-1234*

где id продукта = 3, количество = 1. Дисконтная карта номер - 1234

*параметры:*

**card** - номер карты или id

**productFile** - путь к файлу csv продуктов

**cardFile** - путь к файлу csv карточек 

**printTo** - куда выводить чек: 0 - в файл, 1 - в консоль (если параметр не указан выводится в файл)

### **структура файла product.csv**

id; наименование; цена; товар на акции или нет (true/false)

***пример:***

1;Картофель;23,55;true

2;Яйца;97,61;false

[product.csv](https://github.com/PluM2006/check/blob/master/src/main/resources/product.csv)

### **структура файла card.csv**

id; номер карты ; скидка (сколько процентов)

***пример:***

1;1111 1111 1111 1111;7

2;2222 2222 2222 2222;5

[card.csv](https://github.com/PluM2006/check/blob/master/src/main/resources/card.csv)

Файлы product.csv и card.csv можно поместить в папку с программой и не указывать в параметрах

