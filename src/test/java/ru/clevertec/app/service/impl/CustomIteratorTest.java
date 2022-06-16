package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.service.CustomIterator;

import static org.junit.jupiter.api.Assertions.*;

public class CustomIteratorTest {
    private CustomArrayList<String> list;
    private CustomIterator<String> it;

    @BeforeEach
    public void setUp() throws Exception {
        list = new CustomArrayList<>();
        list.add("Apple");
        list.add("Xiaomi");
        it = list.getIterator();
    }

    @Test
    public void nextTest() {
        assertEquals("Apple", it.next());
    }

    @Test()
    public void testNextException() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () ->{
            it.next();
            it.next();
            it.next();
        });

    }

    @Test
    public void hasNextTest() {
        assertTrue(it.hasNext());
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void removeTest() {
        it.next();
        it.remove();
        assertEquals(list.size(), 1);
        assertEquals(list.find("Apple"), -1);
    }

    @Test
    public void addBeforeTest() {
        String value = "Lenovo";
        it.next();
        it.addBefore(value);
        assertEquals(list.get(0), value);
    }

    @Test
    public void addAfterTest() {
        String value = "Lenovo";
        it.next();
        it.addAfter(value);
        assertEquals(list.get(1), value);
    }

}
