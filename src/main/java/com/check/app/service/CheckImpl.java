package com.check.app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.check.app.entity.Card;
import com.check.app.entity.Check;
import com.check.app.entity.CheckItem;
import com.check.app.entity.Product;

public class CheckImpl implements CheckInteface {
	
	@Override
	public Check getCheck(String[] args) {
		
		ParseArgsInterface pa = new ParseArgsImpl();
		String card = pa.getCard(args, "card");
		List<CheckItem> listItems = pa.getCheckItem(args);
		
		Check check = new Check();
		
		//Расчет скидок
		for (CheckItem checkItem : listItems) {
			//Скидка 10% если товара больше 5
			if (checkItem.getProduct().getSale()) {
				Integer q = listItems.stream()
						.filter(p -> p.getProduct().getId()==checkItem.getProduct().getId())
						.map(x -> x.getQty())
						.reduce(0, Integer::sum);
				if (q>=5) {
					checkItem.setDiscont(new BigDecimal(10).multiply(checkItem.getSumm())
									 .divide(new BigDecimal(100)));
				}
			}
		}
//		List<Product> produckInCheck = pa.getProduct(args);
		
		
		
//		List<CheckItem> listcheckitem = new ArrayList<CheckItem>();
//		for (int i = 0; i < args.length; i++) {
//			String[] a = args[i].split("-");
//			if (a[0].matches("-?\\d+(\\.\\d+)?")) {
//				Product prod = listProduct.stream().filter(p -> p.getId()==Integer.parseInt(a[0])).findAny().orElse(null);
//				CheckItem item = new CheckItem(prod, Integer.parseInt(a[1]), prod.getPrice().multiply(new BigDecimal(a[1])), new BigDecimal(0));
//				listcheckitem.add(item);
//			} else {
//				card = allCard.stream().filter(e -> e.getId()==(Integer.parseInt(a[1]))).findAny().orElse(null);
//				
//			}
//			
//		}

		return check;
	}
	


}
