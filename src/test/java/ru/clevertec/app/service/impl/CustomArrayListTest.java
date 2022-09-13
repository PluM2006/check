package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.customlist.CustomArrayList;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomArrayListTest {

    int sizeListA;
    int sizeListB;
    private Random random;
    private CustomArrayList<Integer> listA;
    private CustomArrayList<Integer> listB;

    @BeforeEach
    public void setUp() throws Exception {
        listA = new CustomArrayList<>();
        listB = new CustomArrayList<>();
        random = new Random();
        sizeListA = random.nextInt(100) + 10;
        sizeListB = random.nextInt(100) + 10;
        getList(sizeListA, listA);
        getList(sizeListB, listB);
    }

    @Test
    public void setMaxSizeTest() {
        int maxSizeL = sizeListA / 2;
        int maxSizeM = sizeListA * 2;
        listA.setMaxSize(maxSizeL);
        assertEquals(maxSizeL, listA.size());
        // Дополним list до + 5
        int k = maxSizeL - listA.size() + 5;
        for (int i = 0; i < k; i++) {
            addItem(listA);
        }
        assertEquals(maxSizeL, listA.size());
        listA.setMaxSize(maxSizeM);
        // Дополним list до конца
        k = maxSizeM - listA.size();
        for (int i = 0; i <= k; i++) {
            addItem(listA);
        }
        assertEquals(maxSizeM, listA.size());
    }

    @Test()
    public void setMaxSizeExceptionTest() {
        int maxSize = -1;
        assertThrows(IllegalArgumentException.class, () -> listA.setMaxSize(maxSize));


    }

    @Test
    public void addTest() {
        listA.add(12);
        assertEquals(listA.size(), sizeListA + 1);
    }

    @Test
    public void addAllTest() {
        listA.addAll(listB);
        assertEquals(listA.size(), sizeListB + sizeListA);
    }

    @Test
    public void setTest() {
        int value = random.nextInt(10);
        int index = random.nextInt(sizeListA);
        listA.set(index, value);
        assertEquals(listA.get(index), Integer.valueOf(value));
    }

    @Test
    public void removeTest() {
        int size = listA.size();
        listA.remove(size / 2);
        assertEquals(size - 1, listA.size());
    }

    @Test
    public void clearTest() {
        listA.clear();
        assertEquals(listA.size(), 0);
    }

    @Test
    public void findTest() {
        int value = random.nextInt();
        listA.set(random.nextInt(listA.size()), value);
        int index = listA.find(value);
        assertEquals(Integer.valueOf(value), listA.get(index));
    }

    @Test
    public void getTest() {
        int value = random.nextInt();
        int index = random.nextInt(listA.size());
        listA.set(index, value);
        assertEquals(Integer.valueOf(value), listA.get(index));
    }

    @Test
    public void toArrayTest() {
        Object[] array = listA.toArray();
        assertEquals(listA.size(), array.length);

    }

    @Test
    public void trimTest() {
        int sizelist = listA.size();
        int valueNull = 0;
        for (int i = 0; i < listA.size(); i++) {
            if (listA.get(i) == null) valueNull++;
        }
        listA.trim();
        assertEquals(listA.size(), sizelist - valueNull);

    }

    private int getList(int size, CustomArrayList<Integer> list) {
        int sizeArray = 0;
        for (int i = 0; i < size; i++) {
            addItem(list);
            sizeArray++;
        }
        return sizeArray;
    }

    private void addItem(CustomArrayList<Integer> list) {
        int max = 100;
        int min = -100;
        int diff = max - min;
        int randomElement = random.nextInt(2);
        if (randomElement == 0) list.add(null);
        else list.add(random.nextInt(diff) + min);
    }

}
