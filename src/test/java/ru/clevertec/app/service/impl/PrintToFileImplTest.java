package ru.clevertec.app.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.app.entity.Check;
import ru.clevertec.app.service.CheckInterface;
import ru.clevertec.app.service.CheckToString;
import ru.clevertec.app.service.PrintInterface;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PrintToFileImplTest {

    PrintInterface printInterface = new PrintToFileImpl();
    CheckInterface checkInterface = new CheckImpl();


    @BeforeEach
    void setUp() {
    }

    @Test
    void print() throws IOException {
        Check check = checkInterface.getCheck(new String[]{"1-1", "3-6", "card-1", "printTo-1", "cardFile-testCard.csv", "productFile-testProduct.csv"});
        CheckToString checkToString = new CheckToString();
        printInterface.print(check);
        String result = checkToString.result(check);
        Path file = Paths.get("check.txt");
        String allLine;
        try (Stream<String> stream = Files.lines(file)) {
            allLine = stream.collect(Collectors.joining("\n"));
        }
        System.out.println(allLine);
        Assertions.assertEquals(allLine, result);
    }
}