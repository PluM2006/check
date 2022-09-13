package ru.clevertec.app.check.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.clevertec.app.check.PrintInterface;

@Component
public class PrintToFileImpl implements PrintInterface {

	@Value("${constants.fileName}")
	private String fileName;
	@Override
	public void print(String check) {
		File file = new File(fileName);
		try (FileWriter myWriter = new FileWriter(file)) {
			myWriter.write(check);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
