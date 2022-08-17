package ru.clevertec.app.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    Product product;

    @BeforeEach
    public void setUp() throws Exception {
        product = new Product(15L, "Яблоко", new BigDecimal("12"), 20, true);
    }

    @Test
    public void getId() {
        Long idExpected = 15L;
        assertEquals(product.getId(), idExpected);
    }

    @Test
    public void setId() {
        Long idExpected = 2L;
        product.setId(idExpected);
        assertEquals(product.getId(), idExpected);
    }

    @Test
    public void getName() {
        assertEquals(product.getName(), "Яблоко");
    }

    @Test
    public void setName() {
        String nameExpected = "Банан";
        product.setName(nameExpected);
        assertEquals(product.getName(), nameExpected);
    }

    @Test
    public void getPrice() {
        assertEquals(product.getPrice(), new BigDecimal("12"));
    }

    @Test
    public void setPrice() {
        BigDecimal priceExpected = new BigDecimal("46");
        product.setPrice(priceExpected);
        assertEquals(product.getPrice(), priceExpected);
    }

    @Test
    public void getSale() {
        assertEquals(product.getSale(), true);
    }

    @Test
    public void setSale() {
        Boolean saleExpected = false;
        product.setSale(saleExpected);
        assertEquals(product.getSale(), saleExpected);
    }

    @Test
    public void getCount() {
        Integer countExpected = 20;
        assertEquals(product.getCount(), countExpected);
    }

    @Test
    public void setCount() {
        Integer countExpected = 46;
        product.setCount(countExpected);
        assertEquals(product.getCount(), countExpected);
    }

    @Test
    public void testEquals() {
        Product productExpected = new Product(15L, "Яблоко", new BigDecimal("12"), 20, true);
        assertTrue(product.equals(productExpected));
    }
    @Test
    public void testEqualsFail() {
        Product productExpected = new Product();
        assertFalse(product.equals(productExpected));
    }

    @Test
    public void testHashCode() {
        Product productExpected = new Product(15L, "Яблоко", new BigDecimal("12"), 20, true);
        assertEquals(product.hashCode(), productExpected.hashCode());
    }

    @Test
    public void testToString() {
        String exception = "Product(id=15, name=Яблоко, price=12, count=20, sale=true)";
        assertEquals(product.toString(), exception);
    }
}