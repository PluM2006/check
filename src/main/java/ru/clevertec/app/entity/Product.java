package ru.clevertec.app.entity;

import lombok.*;

import java.math.BigDecimal;


@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Product {

    private Long id;
    private String name;
    private BigDecimal price = BigDecimal.ZERO;
    private Integer count;
    private Boolean sale = false;

    public Product(Long id, String name, BigDecimal price, Integer count, Boolean sale) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.sale = sale;
    }
}
