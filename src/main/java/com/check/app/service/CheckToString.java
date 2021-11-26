package com.check.app.service;

import java.math.RoundingMode;

import com.check.app.entity.Check;
import com.check.app.entity.CheckItem;

public class CheckToString  {

	
	public String result (Check check) {
		
		String result = "";
		result = result + 
				check.getName()+"\n"+
				check.getAdress()+"\n"+
				check.getDate()+"\n"+
				check.getTime()+"\n"+
			 	String.format("%-3s","QTY ")+
				String.format("%-30s", "DESCRIPION ")+
				String.format("%-7s", "PRICE")+
				String.format("%-6s", "TOTAL")+"\n";
		
		for (CheckItem	ci : check.getCheckItem()) {
			result  = result+
					String.format("%-3s", ci.getQty())+" "+
					String.format("%-30s", ci.getProduct().getName())+
					String.format("%-7s", ci.getProduct().getPrice()+"$")+
					String.format("%s", ci.getSumm()+"$")+"\n";
			if (ci.getPromDiscount()) {
				result  = result+
						String.format("%4s", " ")+
						String.format("%-37s", "акция скидка:")+
						String.format("%s", ci.getSumm().subtract(ci.getDiscount())+"$"+"\n")
						;
			}
		}
		result  = result+
		String.format("%39s", " ").replace(" ", "-")+"\n"+
		String.format("%39s", " ").replace(" ", "-")+"\n"+
		"Итого по чеку: "+check.getSummTotal()+"$"+"\n";
		if (check.getCard()!=null) {
			result  = result+
			"Дисконтная карта: "+check.getCard().getNumbercard()+" скидка: "+check.getCard().getDiscount()+"%"+"\n";
		}
		result  = result+
		"Скидка по чеку: "+check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN)+"$"+"\n"+
		"Итого по чеку со скидкой: "+check.getSummTotal().subtract(check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN))+"\n";
		
		return result;
	}
}
