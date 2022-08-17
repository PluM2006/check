package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.check.impl.CheckItemsDBImpl;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.check.CheckItemsInterface;
import ru.clevertec.app.customlist.CustomList;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CheckItemsDBImplTest {


    Map<Long,Integer> mapParam = new HashMap<>();
    CheckItemsInterface parseArgs;
    CustomList<Long> errorsItem = new CustomArrayList<>();



    @BeforeEach
    public void setUp() throws Exception {
        mapParam.put(1L, 6);
    }
    @Test
    void getCheckItemTest() {
        CheckItem checkItem = new CheckItem(new Product(1L, "Картофель", new BigDecimal("7.31"), 11, true), 6, new BigDecimal("7.31").multiply(new BigDecimal("6")), BigDecimal.ZERO, false);
        CustomList<CheckItem> checkItems = parseArgs.getCheckItem(mapParam, errorsItem);
        assertEquals(checkItems.get(0), checkItem);
    }
}