package ru.clevertec.app.service.check.impl;

import ru.clevertec.app.service.check.interfaces.PrintInterface;
import ru.clevertec.app.utils.CheckFormatBuilder;

public class PrintToConsoleImpl implements PrintInterface {

	@Override
	public void print(String check) {
		System.out.print(CheckFormatBuilder.getCheckResult());
	}

}
