package ru.clevertec.app.check.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ru.clevertec.app.check.PrintInterface;
import ru.clevertec.app.utils.YamlUtils;

public class PrintToFileImpl implements PrintInterface {

	@Override
	public void print(String check) {
		File file = new File(YamlUtils.getYamlProperties().getConstants().getFileName());
		try (FileWriter myWriter = new FileWriter(file)) {
			myWriter.write(check);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
