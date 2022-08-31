package ru.clevertec.app.entity;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private BigDecimal price = BigDecimal.ZERO;
    private Integer count;
    private Boolean sale = false;

}
