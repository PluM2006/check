package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.CustomList;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseArgsImplTest {

    String[] args = {};
    ParseArgsImpl parseArgs = new ParseArgsImpl();


    @BeforeEach
    public void setUp() throws Exception {
        args = new String[]{"1-6", "card-1", "printTo-0"};
    }

    @Test
    public void getCheckItemTest() {
        CheckItem checkItem = new CheckItem(new Product(1L, "Картофель", new BigDecimal("7.31"), 11, true), 6, new BigDecimal("7.31").multiply(new BigDecimal("6")), BigDecimal.ZERO, false);
        CustomList<CheckItem> checkItems = parseArgs.getCheckItem(args);
        assertEquals(checkItems.get(0), checkItem);
    }

    @Test
    public void getCardTest() {
        Optional<Card> card = Optional.of(new Card(1L, "1111 1111 1111 1111", new BigDecimal("10")));
        assertEquals(card, parseArgs.getCard(args, "card"));
    }

    @Test
    public void getPrintToTest() {
        int printTo = 0;
        assertEquals(printTo, parseArgs.getPrintTo(args, "printTo"));
    }
}