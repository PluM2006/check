package com.check.app.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.check.app.entity.Check;
import com.check.app.service.CheckToString;
import com.check.app.service.PrintInterface;

public class PrintToFileImpl implements PrintInterface {

	@Override
	public void print(Check check) {

		CheckToString cts = new CheckToString();
		File file = new File("check.txt");
		try (FileWriter myWriter = new FileWriter(file);) {
			myWriter.write(cts.result(check));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
