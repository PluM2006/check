package com.check.app.service;

import com.check.app.entity.Check;

public class PrintToConsole implements PrintInterface {

	@Override
	public void print(Check check) {
		CheckToString cts = new CheckToString();
		System.out.print(cts.result(check));
	}

}
