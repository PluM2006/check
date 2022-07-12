package ru.clevertec.app.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ru.clevertec.app.constant.Constants;
import ru.clevertec.app.service.PrintInterface;

public class PrintToFileImpl implements PrintInterface {

	@Override
	public void print(String check) {

		File file = new File(Constants.FILE_NAME.getName());
		try (FileWriter myWriter = new FileWriter(file);) {
			myWriter.write(CheckFormatBuilder.getCheckResult().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
