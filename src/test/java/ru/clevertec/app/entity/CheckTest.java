package ru.clevertec.app.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.service.impl.CustomArrayList;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CheckTest {

    Product product;
    Card card;
    CheckItem checkItem;
    CustomList<CheckItem> checkItemList;
    Shop shop;
    Cashier cashier;
    String date;
    String time;
    int printTo;
    Check check;


    @BeforeEach
    public void setUp() throws Exception {
        product = new Product(1L, "Яблоко", new BigDecimal("10"), 50, false);
        card = new Card(1L, "1111 1111 1111 1111", new BigDecimal("10"));
        checkItem = new CheckItem(product, 1, new BigDecimal("10"), BigDecimal.ZERO, false);
        shop = new Shop("Shop", "Inter str. 5");
        cashier = new Cashier("Luke", "007");
        printTo = 1;
        checkItemList = new CustomArrayList<>();
        checkItemList.add(checkItem);
        check = new Check(checkItemList, card, new BigDecimal("10"), new BigDecimal("12"), shop, cashier, date, time, printTo);
    }

    @AfterEach
    public void tearDown() throws Exception {
        check = null;
    }

    @Test
    public void getCheckItem() {
        assertEquals(check.getCheckItem(), checkItemList);
    }

    @Test
    public void setCheckItem() {
        CheckItem checkItemExpected = new CheckItem(product, 15, new BigDecimal("46"), BigDecimal.ZERO, true);
        CustomList<CheckItem> checkItemListExpected = new CustomArrayList<>();
        checkItemListExpected.add(checkItemExpected);
        check.setCheckItem(checkItemListExpected);
        assertEquals(check.getCheckItem(), checkItemListExpected);
    }

    @Test
    public void getCard() {
        assertEquals(check.getCard(), card);
    }

    @Test
    public void setCard() {
        Card cardExpected = new Card(46L, "1234 4568 6548 9879", new BigDecimal("6"));
        check.setCard(cardExpected);
        assertEquals(check.getCard(), cardExpected);
    }

    @Test
    public void getSummTotal() {
        BigDecimal summTotalExpected = new BigDecimal("10");
        assertEquals(check.getSummTotal(), summTotalExpected);
    }

    @Test
    public void setSummTotal() {
        BigDecimal summTotalExpected = new BigDecimal("46");
        check.setSummTotal(summTotalExpected);
        assertEquals(check.getSummTotal(), summTotalExpected);
    }

    @Test
    public void getDiscountTotal() {
        BigDecimal dicountTotalExpected = new BigDecimal("12");
        assertEquals(check.getDiscountTotal(), dicountTotalExpected);
    }

    @Test
    public void setDiscountTotal() {
        BigDecimal dicountTotalExpected = new BigDecimal("46");
        check.setDiscountTotal(dicountTotalExpected);
        assertEquals(check.getDiscountTotal(), dicountTotalExpected);
    }

    @Test
    public void getShop() {
        assertEquals(check.getShop(), shop);
    }

    @Test
    public void setShop() {
        Shop shopExpected = new Shop("Krama 646", "Drezden");
        check.setShop(shopExpected);
        assertEquals(check.getShop(), shopExpected);
    }

    @Test
    public void getCashier() {
        assertEquals(check.getCashier(), cashier);
    }

    @Test
    public void setCashier() {
        Cashier cashierExpected = new Cashier("Darts", "58");
        check.setCashier(cashierExpected);
        assertEquals(check.getCashier(), cashierExpected);
    }

    @Test
    public void getDate() {
        assertEquals(check.getDate(), date);
    }

    @Test
    public void setDate() {
        String dateExpected = "01.01.1991";
        check.setDate(dateExpected);
        assertEquals(check.getDate(), dateExpected);
    }

    @Test
    public void getTime() {
        assertEquals(check.getTime(), time);
    }

    @Test
    public void setTime() {
        String timeExpected = "00:00";
        check.setTime(timeExpected);
        assertEquals(check.getTime(), timeExpected);
    }

    @Test
    public void getPrintTo() {
        assertEquals(check.getPrintTo(), printTo);
    }

    @Test
    public void setPrintTo() {
        int printToExpected = 0;
        check.setPrintTo(printToExpected);
        assertEquals(check.getPrintTo(), printToExpected);
    }

    @Test
    public void testEquals() {
        Check checkExpected = new Check(checkItemList, card, new BigDecimal("10"), new BigDecimal("12"), shop, cashier, date, time, printTo);
        assertTrue(check.equals(checkExpected));
    }

    @Test
    public void testEqualsFail() {
        Check checkExpected = new Check();
        assertFalse(check.equals(checkExpected));
    }

    @Test
    public void testHashCode() {
        Check checkExpected = new Check(checkItemList, card, new BigDecimal("10"), new BigDecimal("12"), shop, cashier, date, time, printTo);
        assertEquals(check.hashCode(), checkExpected.hashCode());
    }

    @Test
    public void testToString() {
        Check checkExpected = new Check(checkItemList, card, new BigDecimal("10"), new BigDecimal("12"), shop, cashier, date, time, printTo);
        assertEquals(check.toString(), checkExpected.toString());
    }
}