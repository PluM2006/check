package com.check.app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.check.app.entity.Check;
import com.check.app.entity.CheckItem;

public class CheckToString  {

	final int lengthChek = 50;
	public String result (Check check) {
		String result = "";
		if (check.getCheckItem().stream().filter(p -> p.getProduct().getName()==null).count() < check.getCheckItem().size()) {
			result = result + 
					String.format("%50s", " ").replace(" ", "-")+"\n"+
					String.format("%" + center(check.getShop().getName().length()) + "s",check.getShop().getName())+"\n"+
					String.format("%" + center(check.getShop().getAdress().length()) + "s",check.getShop().getAdress())+"\n"+
					String.format("%-34s","кассир: #"+check.getCashier().getNumber())+
					String.format("%s", "Дата: "+check.getDate())+"\n"+
					String.format("%-34s", "")+
					String.format("Время:")+String.format("%"+check.getDate().length()+"s", check.getTime())+"\n"+
					String.format("%50s", " ").replace(" ", "-")+"\n"+
				 	String.format("%-4s","кол")+
					String.format("%-30s", "наименование")+
					String.format("%-9s", "цена")+
					String.format("%7s", "всего")+"\n";
			
			for (CheckItem	ci : check.getCheckItem()) {
				if (ci.getProduct().getName()==null) {
					result = "нет продукта с id: "+ci.getProduct().getId()+"\n"+result;
				}else {
					result  = result+
							String.format("%-4s", ci.getQty())+
							String.format("%-30s", ci.getProduct().getName())+
							String.format("%-9s", ci.getProduct().getPrice()+"$")+
							String.format("%7s", ci.getSumm()+"$")+"\n";
					if (ci.getPromDiscount()) {
						result  = result+
								String.format("%6s", " ")+
								String.format("%-37s", "скидка 10%:")+
								String.format("%8s", "-"+ci.getDiscount()+"$"+"\n")+
								String.format("%6s", " ")+
								String.format("%-37s", "цена со скидкой:")+
								String.format("%8s", ci.getSumm().subtract(ci.getDiscount())+"$"+"\n");
					}
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
			if(check.getDiscountTotal().compareTo(BigDecimal.ZERO)!=0) {
				result  = result+
						"Скидка: -"+check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN)+"$"+"\n"+
						"Итого со скидкой: "+check.getSummTotal().subtract(check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN))+"\n";
			}
		} else {
			result = "Чек не сформирован"+"\n"+
					"Не указаны продукты, либо не правильный путь к файлу продуктов";
			for (CheckItem	ci : check.getCheckItem()) {
				if (ci.getProduct().getName()==null) {
					result = "нет продукта с id: "+ci.getProduct().getId()+"\n"+result;
				}
			}
		}
		return result;
	}
	
	public int center(int i) {
		return (lengthChek - i)/2+i;
	}
}
