package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.check.impl.CheckBuilderImpl;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.check.impl.PrintToConsoleImpl;
import ru.clevertec.app.entity.*;
import ru.clevertec.app.check.CheckBuilderInterface;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.check.PrintInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintToConsoleImplTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    PrintInterface printInterface = new PrintToConsoleImpl();
    CheckBuilderInterface checkBuilderInterface;
    Shop shop;
    Cashier cashier;
    Card card;
    CustomList<CheckItem> checkItems = new CustomArrayList<>();

    Map<Long,Integer> mapParam = new HashMap<>();

    @BeforeEach
    public void setUp() throws Exception {
        shop = new Shop("Krama N646", "3-я ул. Строителей, 25");
        cashier = new Cashier("Luke Skywalker", "007");
        card = new Card(1L, "1-1-1-1", new BigDecimal("7"));
        List<Product> productList = List.of(new Product(1L, "banana", new BigDecimal("32.0"), 23, false), new Product(2L, "tomato", new BigDecimal("10.0"), 23, true), new Product(3L, "malina", new BigDecimal("11.0"), 23, false));
        checkItems.add(new CheckItem(productList.get(0), 12, new BigDecimal("10"), new BigDecimal("1"), false));
        checkItems.add(new CheckItem(productList.get(2), 5, new BigDecimal("30"), new BigDecimal("3"), true));
        checkItems.add(new CheckItem(productList.get(1), 10, new BigDecimal("20"), new BigDecimal("2"), false));
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void print() {
        String check = checkBuilderInterface.getCheck(mapParam, card);
        printInterface.print(check);
        assertEquals(check, outContent.toString());
    }
}