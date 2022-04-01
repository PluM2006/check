package com.check.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import com.check.app.entity.Product;
import com.check.app.service.CustomIterator;
import com.check.app.service.CustomList;
import com.check.app.service.impl.CustomArrayList;

public class ListRunner {

	public static void main(String[] args) {
		
		Random random = new Random();
		
		List<String> list = new ArrayList<String>(00);
		System.out.println(list.size());
		CustomList<String> list2 = new CustomArrayList<>(10);
		System.out.println(list2.size());

		List<String> s1 = new ArrayList<>(20);
		s1.add("Прівет");
		s1.add("Прівет2");
		s1.add("Прівет3");
		s1.add("2222");
		s1.add("Прівет5");
		s1.add("2222");
		s1.add("2222");
		s1.add("1");
		s1.remove(7);
		System.out.println(s1.toString());
		System.out.println(s1.size());

		ListIterator<String> it = s1.listIterator();
		while (it.hasNext()) {
			String string = (String) it.next();
			if (string.contains("2")) {
				it.remove();
//				it.remove();
			}
		}
		System.out.println(s1.toString());

		CustomList<String> s = new CustomArrayList<>();
		CustomList<String> s2 = new CustomArrayList<>();

		CustomList<Product> p = new CustomArrayList<>();
		p.add(new Product(2L, "Graim", new BigDecimal(1), 2, false));
		p.add(new Product(3L, "2Graim", new BigDecimal(2), 2, false));
		p.add(new Product(4L, "3Graim", new BigDecimal(3), 2, false));
		p.add(null);
		p.add(new Product(5L, "4Graim", new BigDecimal(4), 2, false));
		p.add(new Product(6L, "5Graim", new BigDecimal(5), 2, false));
		System.out.println(p.get(0));
		p.trim();
		s.add("Прів2ет");
		s.add("Прівет");
		s.add("Прів2ет");
		s.add("Прів2ет");
		s.add(null);
		s2.add("111");
		s2.add("222");
		s.add(null);
		s.addAll(s2);
//		System.out.println(s.get(2));
		s.set(4, "555525");
		s.set(5, null);
//		s.trim();
		System.out.println(s.find(null));
		System.out.println(s.find("555525"));
		System.out.println(s.toString());
		CustomIterator<String> it1 = s.getIterator();
		while (it1.hasNext()) {
			String string = (String) it1.next();
			if (string != null && string.contains("2")) {
//				System.out.println(string);
//				it1.remove();
//				it1.remove();
//				it1.addBefore("Gh2bdtn");
				it1.addAfter("11111121111");
			}
		}
		System.out.println("!!! "+s.toString());
		it1 = s.getIterator();
		while (it1.hasNext()) {
			String string = (String) it1.next();
			if (string != null && string.contains("2")) {
//				System.out.println(string);
				it1.remove();
//				it1.remove();
//				it1.addBefore("Gh2bdtn");
//				it1.addAfter("11111121111");
			}
		}

		System.out.println("!!! "+s.toString());
//		List<String> s = new ArrayList<>();
//		s.add("111");
//		s.add("111");
//		s.add("111");
//		
		System.out.println(s.toArray());
		s.setMaxSize(3);
		s.add("Test");
		s.remove(1);
		s.add("Test");
		s.add("2");
		System.out.println(s.toString());
		s.setMaxSize(10);
		System.out.println(s.toString());
		System.out.println(s2.toString());
		s2.setMaxSize(10);
		System.out.println(s2.toString());
		System.out.println(s2.size());
		for (int i = 1; i <= 10; i++) {
			s2.add(String.valueOf(random.nextInt()));
		}
		System.out.println(s2.toString());
		
		s2.setMaxSize(5);
		System.out.println(s2.toString());
		System.out.println(s2.size());
		s2.add("22");
	}

}

