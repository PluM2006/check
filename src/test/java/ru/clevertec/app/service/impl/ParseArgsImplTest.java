package ru.clevertec.app.service.impl;

import org.junit.Before;
import org.junit.Test;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.utils.CardUtils;
import ru.clevertec.app.utils.ProductUtils;

import static org.junit.Assert.*;

public class ParseArgsImplTest {

    String[] args = {};
    ParseArgsImpl parseArgs = new ParseArgsImpl();
    CustomList<Product> allProduct;
    CustomList<Card> allCard;

    @Before
    public void setUp() throws Exception {
        args = new String[]{"1-1", "2-1", "card-1", "printTo-1"};
        allProduct = ProductUtils.createListProduct();
        allCard = CardUtils.createListCard();

    }

    @Test
    public void getCheckItem() {

        parseArgs.getCheckItem(args);
    }

    @Test
    public void getCard() {
    }

    @Test
    public void getPrintTo() {
    }
}