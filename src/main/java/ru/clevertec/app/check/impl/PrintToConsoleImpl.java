package ru.clevertec.app.check.impl;

import org.springframework.stereotype.Component;
import ru.clevertec.app.check.PrintInterface;
import ru.clevertec.app.utils.CheckStringFormatting;

@Component
public class PrintToConsoleImpl implements PrintInterface {

	@Override
	public void print(String check) {
		System.out.print(check);
	}
}
