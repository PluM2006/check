package ru.clevertec.app.service.impl;

import ru.clevertec.app.entity.Check;
import ru.clevertec.app.service.CheckToString;
import ru.clevertec.app.service.PrintInterface;

public class PrintToConsoleImpl implements PrintInterface {

	@Override
	public void print(Check check) {
		CheckToString cts = new CheckToString();
		System.out.print(cts.result(check));
	}

}
