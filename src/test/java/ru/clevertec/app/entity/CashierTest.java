package ru.clevertec.app.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CashierTest {

    Cashier cashier;

    @BeforeEach
    public void setUp() {
        cashier = new Cashier("Luke", "007");

    }

    @Test
    public void getName() {
        assertEquals(cashier.getName(), "Luke");
    }

    @Test
    public void setName() {
        String expectedName = "Darth";
        cashier.setName(expectedName);
        assertEquals(expectedName, cashier.getName());
    }

    @Test
    public void getNumber() {
        assertEquals(cashier.getNumber(), "007");
    }

    @Test
    public void setNumber() {
        String expectedNumber = "008";
        cashier.setNumber(expectedNumber);
        assertEquals(cashier.getNumber(), expectedNumber);
    }

    @Test
    public void testEquals() {
        Cashier cashierExpected = new Cashier("Luke", "007");
        assertTrue(cashierExpected.equals(cashier));
    }
    @Test
    public void testEqualsFiled() {
        Cashier cashierExpected = new Cashier();
        assertFalse(cashierExpected.equals(cashier));
    }

    @Test
    public void testHashCode() {
        Cashier cashierExpected = new Cashier("Luke", "007");
        assertEquals(cashierExpected.hashCode(), cashier.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Cashier{name='Luke', number='007'}";
        assertEquals(cashier.toString(), expected);
    }
}