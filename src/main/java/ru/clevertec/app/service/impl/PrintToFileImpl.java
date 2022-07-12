package ru.clevertec.app.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import ru.clevertec.app.entity.Check;
import ru.clevertec.app.service.CheckToString;
import ru.clevertec.app.service.PrintInterface;

public class PrintToFileImpl implements PrintInterface {
	public static final String FILE_NAME = "check.txt";
	@Override
	public void print(Check check) {

		CheckToString cts = new CheckToString();
		File file = new File(FILE_NAME);
		try (FileWriter myWriter = new FileWriter(file, StandardCharsets.UTF_8)) {
			myWriter.write(cts.result(check));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
