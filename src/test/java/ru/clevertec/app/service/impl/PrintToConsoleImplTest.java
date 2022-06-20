package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.entity.Check;
import ru.clevertec.app.service.CheckInterface;
import ru.clevertec.app.service.CheckToString;
import ru.clevertec.app.service.PrintInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrintToConsoleImplTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    CheckInterface checkInterface = new CheckImpl();

    PrintInterface printInterface = new PrintToConsoleImpl();

    @BeforeEach
    public void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void print() {
        CheckToString checkToString = new CheckToString();
        Check check = checkInterface.getCheck(new String[]{"1-1", "printTo-1"});
        String result = checkToString.result(check);
        printInterface.print(check);
        assertEquals(result, outContent.toString());
    }
}