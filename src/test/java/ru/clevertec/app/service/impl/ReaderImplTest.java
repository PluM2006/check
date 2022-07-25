package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.interfaces.CustomList;
import ru.clevertec.app.service.interfaces.ReaderInterface;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ReaderImplTest {

    ReaderInterface readerInterface = new ReaderImpl();
    CustomList<Product> productCustomList;
    CustomList<Card> cardCustomList;

    @BeforeEach
    public void setUp() throws Exception {
        productCustomList = readerInterface.getAllProduct("testProduct.csv");
        cardCustomList = readerInterface.getAllCard("testCard.csv");
    }

    @Test
    public void getAllProduct() {
        int size = 10;
        assertEquals(productCustomList.size(), size);
    }

    @Test
    public void getAllProductFind() {
        assertEquals(productCustomList.get(9), new Product(10L, "Кефир", new BigDecimal("1.00"), 18, true));
    }

    @Test
    public void getAllProductFail() {
        CustomList<Product> productCustomListExpected = new CustomArrayList<>();
        assertNotEquals(productCustomList, productCustomListExpected);
    }

    @Test
    public void getAllCardTest() {
        int size = 3;
        assertEquals(cardCustomList.size(), size);
    }
}