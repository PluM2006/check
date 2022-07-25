package ru.clevertec.app.service.impl;

import ru.clevertec.app.service.CheckFormatBuilder;
import ru.clevertec.app.service.interfaces.PrintInterface;

public class PrintToConsoleImpl implements PrintInterface {

	@Override
	public void print(String check) {
		System.out.print(CheckFormatBuilder.getCheckResult());
	}

}
