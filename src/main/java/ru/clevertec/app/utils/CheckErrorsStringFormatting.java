package ru.clevertec.app.utils;

import ru.clevertec.app.customlist.CustomList;

public class CheckErrorsStringFormatting {


    public void errorCheckItems(StringBuilder stringBuilderError, CustomList<Long> errorItems) {
        for (Long ci : errorItems) {
            stringBuilderError.insert(0, "Нет продукта с id: " + ci + "\n");
        }
    }

    public void errorCardFile(StringBuilder stringBuilderError) {
        stringBuilderError.append("Нет файла card.csv, либо не правильный путь к файлу дискотных карт" + "\n");
    }

    public void errorProductFIle(StringBuilder stringBuilderError) {
        stringBuilderError.append("Нет файла product.csv, либо не правильный путь к файлу продуктов" + "\n");
    }

    public void errorCheck(StringBuilder stringBuilderError) {
        stringBuilderError.append("Чек не сформирован" + "\n");
        stringBuilderError.append("Не указаны продукты");
    }
}
