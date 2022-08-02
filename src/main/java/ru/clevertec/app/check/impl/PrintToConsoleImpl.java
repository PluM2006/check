package ru.clevertec.app.check.impl;

import ru.clevertec.app.check.PrintInterface;
import ru.clevertec.app.utils.CheckStringFormatting;

public class PrintToConsoleImpl implements PrintInterface {

	@Override
	public void print(String check) {
		System.out.print(CheckStringFormatting.getCheckResult());
	}

}
