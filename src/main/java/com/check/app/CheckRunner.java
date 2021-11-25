package com.check.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.check.app.entity.Card;
import com.check.app.entity.Check;
import com.check.app.entity.CheckItem;
import com.check.app.entity.Product;
import com.check.app.service.CheckImpl;
import com.check.app.service.ReaderImpl;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CheckRunner {
	
	
	public static void main(String[] args) throws FileNotFoundException {
		 final BigDecimal discountp = new BigDecimal(10);
		 
		 

		 //****��������� ���
		 CheckImpl checkImpl = new CheckImpl();
		 checkImpl.getCheck(args);
				 
		 
		 
		 
		 List<CheckItem> listcheckitem = new ArrayList<CheckItem>();
		 Card card = null;
		 
		 Check check = new Check(); 
		 //**************************������� ������ (�������� ��������)
		 

		for (CheckItem ci: listcheckitem) {
			//������ ������ �� ����� ���� >5 ����� ������ 10% 
			if (ci.getProduct().getSale()) {
				Integer q = listcheckitem.stream().filter(p -> p.getProduct().getId()==ci.getProduct().getId()).map(x -> x.getQty()).reduce(0, Integer::sum);
				if (q >=5 ) {
					ci.setDiscont(discountp.multiply(ci.getSumm())
									 .divide(new BigDecimal(100)));
				}
			}
			//����� ����� �������
			check.setSummTotal(check.getSummTotal().add(ci.getSumm()));
			//����� ������
			check.setDiscountTotal(check.getDiscountTotal().add(ci.getDiscont()));
			//���� �� ����� ��� ��� ������ � ���� ����� ������ ������
			if ((ci.getDiscont().compareTo(BigDecimal.ZERO)==0) && (card!=null)){
				check.setDiscountTotal(check.getDiscountTotal()
						.add(ci.getSumm().multiply(new BigDecimal(card.getDiscount()).divide(new BigDecimal(100)))));
			}
		}
		 //������ ����
		 BigDecimal totalsumm = new BigDecimal(0);
		 BigDecimal totalsummdis = new BigDecimal(0);
		 int index = 1;
		 System.out.println(
				 	String.format("%-3s","QTY ")+
					String.format("%-20s", "DESCRIPION ")+
					String.format("%-7s", "PRICE")+
					String.format("%-6s", "TOTAL"));
		 for (CheckItem cItem : listcheckitem) {
			//���� ����� ������ 10% 
//			if (cItem.getProduct().getSale() && cItem.getQty()>=5) {
//				 cItem.setDiscont(cItem.getSumm()
//						 .subtract(discountp.multiply(cItem.getSumm())
//								 .divide(new BigDecimal(100))));
//			}
			
			totalsumm = totalsumm.add(cItem.getSumm());
			
			totalsummdis = totalsummdis.add(cItem.getDiscont());
			if (cItem.getDiscont().compareTo(BigDecimal.ZERO)==0) {
				if (card!=null) {
					totalsummdis = totalsummdis.add(cItem.getSumm()
							.subtract(new BigDecimal(card.getDiscount())
									.multiply(cItem.getSumm().divide(new BigDecimal(100)))));
				} else {
					totalsummdis = totalsummdis.add(cItem.getSumm());
				}
			}
			
			System.out.println(
						String.format("%-3s", cItem.getQty())+" "+
						String.format("%-20s", cItem.getProduct().getName())+
						String.format("%-7s", cItem.getProduct().getPrice()+"$")+
						String.format("%s", cItem.getSumm()+"$")
						);
			if (cItem.getDiscont().compareTo(BigDecimal.ZERO)!=0) {
				System.out.println(
						String.format("%4s", " ")+
						String.format("%-27s", "����� ������:")+
						String.format("%s", cItem.getSumm().subtract(cItem.getDiscont())+"$")
						);
			}
			index++;
		}
		System.out.println(String.format("%39s", " ").replace(" ", "-"));
		System.out.println(String.format("%39s", " ").replace(" ", "-"));
		System.out.println("����� �� ����: "+totalsumm+"$");
		System.out.println("����� �� ����: "+check.getSummTotal()+"$");
//		System.out.println("���������� �����: "+card.getNumbercard()+" "+card.getDiscount()+"%");
		System.out.println("������ �� ����:! "+check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN)+"$");
		System.out.println("����� �� ���� �� �������:! "+check.getSummTotal().subtract(check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN)));
		System.out.println("����� �� ���� �� �������: "+totalsummdis.setScale(2, RoundingMode.HALF_DOWN));
		 
		try {
		      File myObj = new File("filename.txt");
		      if (myObj.createNewFile()) {
//		        System.out.println("File created: " + myObj.getName());
		      } else {
//		        System.out.println("File already exists.");
		      }
		      FileWriter myWriter = new FileWriter(myObj);
		      myWriter.write("��� ���" + args[0]);
		      myWriter.close();
		    } catch (IOException e) {
//		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }

	}
	

	

}
////*****************************************************************
//Card card = new Card();
//if (listCard.size()>1) {
//	 int index = 1;
//	 Scanner in = new Scanner(System.in);
//	 for (Card c: listCard) {
//		 System.out.println(c.getId()+" "+c.getNumbercard() );
//		 index ++;
//	 }
//	 System.out.println("�������� ������ ��������� �����:");
//	 int numcard = in.nextInt();
//	 card = listCardall.stream().filter(e -> e.getId()==(numcard)).findAny().orElse(null);
//	 System.out.println("����� :"+card.getNumbercard());
//	 
//	 
//} else {
//	 card = listCard.get(0);
//}
////*********************������ �����
