package ru.clevertec.app.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CheckItemTest {

    Product product;
    Integer qty;
    BigDecimal summ;
    BigDecimal discount;
    Boolean promoDiscount;
    CheckItem checkItem;


    @BeforeEach
    public void setUp() throws Exception {
        product = new Product(1L, "Banana", new BigDecimal("100"), 46, true);
        qty = 5;
        summ = new BigDecimal("500");
        discount = new BigDecimal("10");
        promoDiscount = true;
        checkItem = new CheckItem(product, qty, summ, discount, promoDiscount);
    }

    @Test
    public void getProduct() {
        assertEquals(checkItem.getProduct(), product);
    }

    @Test
    public void setProduct() {
        Product productExpected = new Product(1L, "Banana", new BigDecimal("100"), 46, true);
        checkItem.setProduct(productExpected);
        assertEquals(checkItem.getProduct(), productExpected);
    }

    @Test
    public void getQty() {
        assertEquals(checkItem.getQty(), qty);
    }

    @Test
    public void setQty() {
        Integer qtyExpected =  6000;
        checkItem.setQty(qtyExpected);
        assertEquals(checkItem.getQty(), qtyExpected);
    }

    @Test
    public void getSumm() {
        assertEquals(checkItem.getSumma(), summ);
    }

    @Test
    public void setSumm() {
        BigDecimal summExpected = new BigDecimal("158");
        checkItem.setSumma(summExpected);
        assertEquals(checkItem.getSumma(), summExpected);
    }

    @Test
    public void getDiscount() {
        assertEquals(checkItem.getDiscount(), discount);
    }

    @Test
    public void setDiscount() {
        BigDecimal discountExpected = new BigDecimal("1005");
        checkItem.setDiscount(discountExpected);
        assertEquals(checkItem.getDiscount(), discountExpected);
    }

    @Test
    public void getPromDiscount() {
        assertEquals(checkItem.getPromDiscount(), promoDiscount);
    }

    @Test
    public void setPromDiscount() {
        Boolean promoDiscountExpected = false;
        checkItem.setPromDiscount(promoDiscountExpected);
        assertEquals(checkItem.getPromDiscount(), promoDiscountExpected);
    }

    @Test
    public void testEquals() {
        CheckItem checkItemExpected = new CheckItem(product, qty, summ, discount, promoDiscount);
        assertTrue(checkItem.equals(checkItemExpected));
    }
    @Test
    public void testEqualsFail() {
        CheckItem checkItemExpected = new CheckItem();
        assertFalse(checkItem.equals(checkItemExpected));
    }

    @Test
    public void testHashCode() {
        CheckItem checkItemExpected = new CheckItem(product, qty, summ, discount, promoDiscount);
        assertEquals(checkItem.hashCode(), checkItemExpected.hashCode());
    }

    @Test
    public void testToString() {
        CheckItem checkItemExpected = new CheckItem(product, qty, summ, discount, promoDiscount);
        assertEquals(checkItem.toString(), checkItem.toString());
    }
}