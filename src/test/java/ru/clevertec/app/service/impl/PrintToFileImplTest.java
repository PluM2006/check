package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.entity.*;
import ru.clevertec.app.service.CheckInterface;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.service.PrintInterface;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PrintToFileImplTest {

    PrintInterface printInterface = new PrintToFileImpl();
    CheckInterface checkInterface = new CheckImpl();
    Shop shop;
    Cashier cashier;

    Card card;
    CustomList<CheckItem> checkItems = new CustomArrayList<>();

    @BeforeEach
    void setUp() {
        shop = new Shop("Krama N646", "3-я ул. Строителей, 25");
        cashier = new Cashier("Luke Skywalker", "007");
        card = new Card(1L, "1-1-1-1", new BigDecimal("7"));
        List<Product> productList = List.of(
                new Product(1L, "banana", new BigDecimal("32.0"), 23, false),
                new Product(2L, "tomato", new BigDecimal("10.0"), 23, true),
                new Product(3L, "malina", new BigDecimal("11.0"), 23, false)
        );
        checkItems.add(new CheckItem(productList.get(0), 12, new BigDecimal("10"), new BigDecimal("1"), false));
        checkItems.add(new CheckItem(productList.get(2), 5, new BigDecimal("30"), new BigDecimal("3"), true));
        checkItems.add(new CheckItem(productList.get(1), 10, new BigDecimal("20"), new BigDecimal("2"), false));
    }

    @Test
    void print() throws IOException {
        String check = checkInterface.getCheck(checkItems, card, shop,  cashier);
        printInterface.print(check);
        Path file = Paths.get("check.txt");
        String allLine;
        try (Stream<String> stream = Files.lines(file)) {
            allLine = stream.collect(Collectors.joining("\n"));
        }
        Assertions.assertEquals(allLine+"\n", check);
    }
}