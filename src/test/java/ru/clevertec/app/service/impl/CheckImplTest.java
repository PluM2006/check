package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.entity.Check;
import ru.clevertec.app.service.CheckInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CheckImplTest {

    CheckInterface checkInterface = new CheckImpl();
    String[] args = {};
    Long id;
    Integer qty;
    Long cardId;

    @BeforeEach
    public void setUp() throws Exception {
        id = 1L;
        qty = 6;
        cardId = 1L;
        args = new String[]{id + "-" + qty, "2-1", "card-" + cardId, "printTo-0"};
    }

    @Test
    public void getCheck() {
        Check check = checkInterface.getCheck(args);
        assertEquals(check.getCheckItem().get(0).getProduct().getId(), id);
    }

    @Test
    public void getCheckQty() {
        Check check = checkInterface.getCheck(args);
        assertEquals(check.getCheckItem().get(0).getQty(), qty);
    }

    @Test
    public void getCheckFail() {
        Check check = checkInterface.getCheck(args);
        assertNotNull(check);
    }
}