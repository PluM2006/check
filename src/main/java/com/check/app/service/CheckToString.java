package com.check.app.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.check.app.entity.Check;
import com.check.app.entity.CheckItem;

public class CheckToString {

	private static final int LENGS_CHECK = 50;

	public String result(Check check) {
		StringBuilder result = new StringBuilder();
		long nullName = check.getCheckItem().stream().filter(p -> p.getProduct().getName() == null).count();
		if (nullName < check.getCheckItem().size()) {
			result.append(String.format("%50s", " ").replace(" ", "-") + "\n");
			result.append(
					String.format("%" + center(check.getShop().getName().length()) + "s", check.getShop().getName())
							+ "\n");
			result.append(
					String.format("%" + center(check.getShop().getAdress().length()) + "s", check.getShop().getAdress())
							+ "\n");
			result.append(String.format("%-34s", "������: #" + check.getCashier().getNumber()));
			result.append(String.format("%s", "����: " + check.getDate()) + "\n");
			result.append(String.format("%-34s", ""));
			result.append(String.format("�����:"));
			result.append(String.format("%" + check.getDate().length() + "s", check.getTime()) + "\n");
			result.append(String.format("%50s", " ").replace(" ", "-") + "\n");
			result.append(String.format("%-4s", "���"));
			result.append(String.format("%-30s", "������������"));
			result.append(String.format("%-9s", "����"));
			result.append(String.format("%7s", "�����") + "\n");

			for (CheckItem ci : check.getCheckItem()) {
				if (ci.getProduct().getName() == null) {
					result.insert(0, "��� �������� � id: " + ci.getProduct().getId() + "\n");
				} else {
					result.append(String.format("%-4s", ci.getQty()));
					result.append(String.format("%-30s", ci.getProduct().getName()));
					result.append(String.format("%-9s", ci.getProduct().getPrice() + "$"));
					result.append(String.format("%7s", ci.getSumm() + "$") + "\n");
					if (ci.getPromDiscount()) {
						result.append(String.format("%6s", " "));
						result.append(String.format("%-37s", "������ 10%:"));
						result.append(String.format("%8s", "-" + ci.getDiscount() + "$" + "\n"));
						result.append(String.format("%6s", " "));
						result.append(String.format("%-37s", "���� �� �������:"));
						result.append(String.format("%8s", ci.getSumm().subtract(ci.getDiscount()) + "$" + "\n"));
					}
				}
			}

			result.append(String.format("%50s", " ").replace(" ", "-") + "\n");
			result.append(String.format("%50s", " ").replace(" ", "-") + "\n");
			result.append("�����: " + check.getSummTotal() + "$" + "\n");
			if (check.getCard() != null) {
				result.append("���������� �����: " + check.getCard().getNumbercard() + " ������: "
						+ check.getCard().getDiscount() + "%" + "\n");
			}
			if (check.getDiscountTotal().compareTo(BigDecimal.ZERO) != 0) {
				result.append("������: -" + check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN) + "$" + "\n");
				result.append("����� �� �������: "
						+ check.getSummTotal().subtract(check.getDiscountTotal().setScale(2, RoundingMode.HALF_DOWN))
						+ "\n");
			}
		} else {
			result.append("��� �� �����������" + "\n");
			result.append("�� ������� ��������, ���� �� ���������� ���� � ����� ���������");
			for (CheckItem ci : check.getCheckItem()) {
				if (ci.getProduct().getName() == null) {
					result.insert(0, "��� �������� � id: " + ci.getProduct().getId() + "\n");
				}
			}
		}
		return result.toString();
	}

	private int center(int i) {
		return (LENGS_CHECK - i) / 2 + i;
	}
}
