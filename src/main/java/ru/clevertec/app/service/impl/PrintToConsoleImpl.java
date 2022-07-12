package ru.clevertec.app.service.impl;

import ru.clevertec.app.service.PrintInterface;

public class PrintToConsoleImpl implements PrintInterface {

	@Override
	public void print(String check) {
		System.out.print(CheckFormatBuilder.getCheckResult());
	}

}
