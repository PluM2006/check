package ru.clevertec.app.entity;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class CheckItem {

    private Product product;
    private Integer qty;
    private BigDecimal summa = BigDecimal.ZERO;
    private BigDecimal discount = BigDecimal.ZERO;
    private Boolean promDiscount = false;

}
