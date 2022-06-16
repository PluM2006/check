package ru.clevertec.app.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    Card card;
    Card cardEmpty;
    Long[] longDrrays;
    String[] stringArrays;
    BigDecimal[] bigDecimalArrays;

    @BeforeEach
    public void setUp() {
        card = new Card(1L, "1111 1111 1111 1111", new BigDecimal("10"));
        cardEmpty = new Card();
        longDrrays = new Long[]{1L, 2L, 3L};
        stringArrays = new String[]{"1111 1111 1111 1111", "2222 2222 2222 2222", "3333 3333 3333 3333"};
        bigDecimalArrays = new BigDecimal[]{new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("30")};
    }

    @Test
    public void getId() {
        assertEquals(longDrrays[0], card.getId());
    }

    @Test
    public void getIdFiledTest() {
        assertNotEquals(longDrrays[1], card.getId());
    }

    @Test
    public void setId() {
        cardEmpty.setId(longDrrays[2]);
        card.setId(longDrrays[1]);
        assertEquals(cardEmpty.getId(), longDrrays[2]);
        assertEquals(card.getId(), longDrrays[1]);
    }

    @Test
    public void getNumbercard() {
        assertEquals(card.getNumbercard(), stringArrays[0]);
    }

    @Test
    public void setNumbercard() {
        cardEmpty.setNumbercard(stringArrays[2]);
        card.setNumbercard(stringArrays[1]);
        assertEquals(cardEmpty.getNumbercard(), stringArrays[2]);
        assertEquals(card.getNumbercard(), stringArrays[1]);
    }

    @Test
    public void getDiscount() {
        assertEquals(card.getDiscount(), bigDecimalArrays[0]);
    }

    @Test
    public void setDiscount() {
        cardEmpty.setDiscount(bigDecimalArrays[2]);
        card.setDiscount(bigDecimalArrays[1]);
        assertEquals(bigDecimalArrays[2], cardEmpty.getDiscount());
        assertEquals(bigDecimalArrays[1], card.getDiscount());
    }

    @Test
    public void testEquals() {
        Card cardExpected  = new Card(1L, "1111 1111 1111 1111", new BigDecimal("10"));
        assertTrue(card.equals(cardExpected));
    }

    @Test
    public void testHashCode() {
        Card cardExpected = new Card(1L, "1111 1111 1111 1111", new BigDecimal("10"));
        assertEquals(card.hashCode(), cardExpected.hashCode());
    }

    @Test
    public void testToString() {
        String expected = "Card{id=1, numbercard='1111 1111 1111 1111', discount=10}";
        assertEquals(card.toString(), expected);
    }
}