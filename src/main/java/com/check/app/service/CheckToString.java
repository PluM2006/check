package com.check.app.service;

import java.math.RoundingMode;

import com.check.app.entity.Check;
import com.check.app.entity.CheckItem;

public class CheckToString  {

	final int lengthChek = 50;
	public String result (Check check) {
		
		String result = "";
		result = result + 
				String.format("%" + center(check.getShop().getName().length()) + "s",check.getShop().getName())+"\n"+
				String.format("%" + center(check.getShop().getAdress().length()) + "s",check.getShop().getAdress())+"\n"+
				String.format("cashier: "+check.getCashier().getNumber())+
				String.format("%"+(lengthChek-check.getCashier().getNumber().length()-9)+"s", "DATE: "+check.getDate())+"\n"+
				String.format("%"+(lengthChek-2)+"s", "TIME: "+check.getTime())+"\n"+
				String.format("%50s", " ").replace(" ", "-")+"\n"+
			 	String.format("%-4s","QTY")+
				String.format("%-30s", "DESCRIPION")+
				String.format("%-9s", "PRICE")+
				String.format("%5s", "TOTAL")+"\n";
		
		for (CheckItem	ci : check.getCheckItem()) {
			result  = result+
					String.format("%-4s", ci.getQty())+
					String.format("%-30s", ci.getProduct().getName())+
					String.format("%-9s", ci.getProduct().getPrice()+"$")+
					String.format("%7s", ci.getSumm()+"$")+"\n";
			if (ci.getPromDiscount()) {
				result  = result+
						String.format("%6s", " ")+
						String.format("%-37s", "скидка 10%:")+
						String.format("%s", "-"+ci.getDiscount()+"$"+"\n")+
						String.format("%6s", " ")+
						String.format("%-37s", "цена со скидкой:")+
						String.format("%s", ci.getSumm().subtract(ci.getDiscount())+"$"+"\n")
						;
			}
		}
		result  = result+
		String.format("%50s", " ").replace(" ", "-")+"\n"+
		String.format("%50s", " ").replace(" ", "-")+"\n"+
		"Итого: "+check.getSummTotal()+"$"+"\n";
		if (check.getCard()!=null) {
			result  = result+
			"Дисконтная карта: "+check.getCard().getNumbercard()+" скидка: "+check.getCard().getDiscount()+"%"+"\n";
		}
		result  = result+
		"Скидка: "+check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN)+"$"+"\n"+
		"Итого со скидкой: "+check.getSummTotal().subtract(check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN))+"\n";
		
		return result;
	}
	public int center(int i) {
		return (lengthChek - i)/2+i;
	}
}
