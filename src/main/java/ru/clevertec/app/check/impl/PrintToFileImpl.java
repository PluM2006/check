package ru.clevertec.app.check.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ru.clevertec.app.check.PrintInterface;
import ru.clevertec.app.utils.PropertiesUtil;
import ru.clevertec.app.utils.CheckStringFormatting;

public class PrintToFileImpl implements PrintInterface {

	@Override
	public void print(String check) {

		File file = new File(PropertiesUtil.get("FILE_NAME"));
		try (FileWriter myWriter = new FileWriter(file)) {
			myWriter.write(CheckStringFormatting.getCheckResult());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
