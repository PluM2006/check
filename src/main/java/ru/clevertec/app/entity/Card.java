package ru.clevertec.app.entity;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Card {

    private Long id;
    private String numberCard;
    private BigDecimal discount;

}
