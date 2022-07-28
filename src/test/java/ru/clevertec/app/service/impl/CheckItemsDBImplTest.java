package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.service.check.impl.CheckItemsDBImpl;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.check.interfaces.CheckItemsInterface;
import ru.clevertec.app.service.customlist.CustomList;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CheckItemsDBImplTest {

    String[] args = {};
    CheckItemsInterface parseArgs = new CheckItemsDBImpl();


    @BeforeEach
    public void setUp() throws Exception {
        args = new String[]{"1-6", "card-1", "printTo-0"};
    }
    @Test
    void getCheckItemTest() {
        CheckItem checkItem = new CheckItem(new Product(1L, "Картофель", new BigDecimal("7.31"), 11, true), 6, new BigDecimal("7.31").multiply(new BigDecimal("6")), BigDecimal.ZERO, false);
        CustomList<CheckItem> checkItems = parseArgs.getCheckItem(args);
        assertEquals(checkItems.get(0), checkItem);
    }
}