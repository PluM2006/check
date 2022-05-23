package ru.clevertec.app.utils;

import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.service.impl.CustomArrayList;

import java.math.BigDecimal;

public class ProductUtils {

    public static CustomList<Product> createListProduct() {
        CustomList<Product> productCustomList = new CustomArrayList<>();
        productCustomList.add(new Product(1L, "Картошка", new BigDecimal(10), 11, false));
        productCustomList.add(new Product(2L, "Огурец", new BigDecimal(20), 15, true));
        productCustomList.add(new Product(3L, "Помидор", new BigDecimal(125), 100, false));
        productCustomList.add(new Product(4L, "Гранат", new BigDecimal(256), 56, true));
        productCustomList.add(new Product(5L, "Хлеб", new BigDecimal(365), 100, false));
        productCustomList.add(new Product(6L, "Банан", new BigDecimal(125), 152, true));
        return productCustomList;
    }
}
