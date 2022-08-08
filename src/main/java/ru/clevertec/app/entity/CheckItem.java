package ru.clevertec.app.entity;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CheckItem {

    private Product product;
    private Integer qty;
    private BigDecimal summa = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private Boolean promDiscount = false;

    public CheckItem(Product product, Integer qty, BigDecimal summa, BigDecimal discount, Boolean promDiscount) {
        this.product = product;
        this.qty = qty;
        this.summa = summa;
        this.discount = discount;
        this.promDiscount = promDiscount;
    }
}
