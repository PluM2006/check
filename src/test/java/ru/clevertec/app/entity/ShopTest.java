package ru.clevertec.app.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShopTest {

    Shop shop;

    @BeforeEach
    public void setUp() throws Exception {
        shop = new Shop(1L, "Krama", "Gomel, Rumyancevskaya str., 5");
    }

    @Test
    public void getName() {
        String nameExpected = "Krama";
        assertEquals(shop.getName(), nameExpected);
    }

    @Test
    public void setName() {
        String nameExpected = "Shop";
        shop.setName(nameExpected);
        assertEquals(shop.getName(), nameExpected);
    }

    @Test
    public void getAdress() {
        String adressExpected = "Gomel, Rumyancevskaya str., 5";
        assertEquals(shop.getAddress(), adressExpected);
    }

    @Test
    public void setAdress() {
        String adressExpected = "Mensk, Gaspadarchaya str., 153";
        shop.setAddress(adressExpected);
        assertEquals(shop.getAddress(), adressExpected);
    }

    @Test
    public void testEquals() {
        Shop shopExpected = new Shop(1L, "Krama", "Gomel, Rumyancevskaya str., 5");
        assertTrue(shop.equals(shopExpected));
    }
    @Test
    public void testEqualsFail() {
        Shop shopExpected = new Shop();
        assertFalse(shop.equals(shopExpected));
    }

    @Test
    public void testHashCode() {
        Shop shopExpected = new Shop(1L, "Krama", "Gomel, Rumyancevskaya str., 5");
        assertEquals(shop.hashCode(), shopExpected.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Shop{id=1, name='Krama', address='Gomel, Rumyancevskaya str., 5'}";
        assertEquals(shop.toString(), expected);
    }
}