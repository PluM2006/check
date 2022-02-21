package com.check.app.service.impl;

import com.check.app.entity.Check;
import com.check.app.service.CheckToString;
import com.check.app.service.PrintInterface;

public class PrintToConsoleImpl implements PrintInterface {

	@Override
	public void print(Check check) {
		CheckToString cts = new CheckToString();
		System.out.print(cts.result(check));
	}

}
