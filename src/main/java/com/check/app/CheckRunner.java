package com.check.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.check.app.entity.Check;
import com.check.app.service.CheckInteface;
import com.check.app.service.MyIterator;
import com.check.app.service.MyList;
import com.check.app.service.PrintInterface;
import com.check.app.service.impl.CheckImpl;
import com.check.app.service.impl.MyArray;
import com.check.app.service.impl.PrintToConsoleImpl;
import com.check.app.service.impl.PrintToFileImpl;

public class CheckRunner {

	public static void main(String[] args) {
		CheckInteface checkImpl = new CheckImpl();
		Check check = checkImpl.getCheck(args);
		if (check.getPrintTo() == 0) {
			PrintInterface file = new PrintToFileImpl();
			file.print(check);
		}
		if (check.getPrintTo() == 1) {
			PrintInterface cons = new PrintToConsoleImpl();
			cons.print(check);
		}
		List<String> s1 = new ArrayList<>();
		s1.add("Прівет");
		s1.add("Прівет2");
		s1.add("Прівет3");
		
		
//		System.out.println(s1.get(3));
		Iterator<String> it = s1.iterator();
		while (it.hasNext()) {
			String string = (String) it.next();
			if (string.contains("2")) {
				it.remove();
			}
		}
		System.out.println(s1);
		
		
		
		
		MyList<String> s = new MyArray<>();
		MyList<String> s2 = new MyArray<>();
		
		s.add("Прівет");
		s.add("Прівет2");
		s.add("Прівет3");
		s2.add("111");
		s2.add("222");
		
		
		s.addAll(s2);
//		System.out.println(s.get(2));
		MyIterator<String> it1 = s.getIterator();
		while (it1.hasNext()) {
			String string = (String) it1.next();
			if (string.contains("2")) {
				it1.remove();
//				it1.addBefore();
			}
		}
		System.out.println(s);
//		List<String> s = new ArrayList<>();
//		s.add("111");
//		s.add("111");
//		s.add("111");
//		
//		System.out.println(s.toArray());

	}
}
