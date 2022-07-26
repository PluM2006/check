package ru.clevertec.app.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ru.clevertec.app.service.utils.PropertiesUtil;
import ru.clevertec.app.service.utils.CheckFormatBuilder;
import ru.clevertec.app.service.interfaces.PrintInterface;

public class PrintToFileImpl implements PrintInterface {

	@Override
	public void print(String check) {

		File file = new File(PropertiesUtil.get("FILE_NAME"));
		try (FileWriter myWriter = new FileWriter(file);) {
			myWriter.write(CheckFormatBuilder.getCheckResult());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
