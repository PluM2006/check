package ru.clevertec.app.entity;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Card {

    private Long id;
    private String numberCard;
    private BigDecimal discount;


    public Card(Long id, String numberCard, BigDecimal discount) {
        this.id = id;
        this.numberCard = numberCard;
        this.discount = discount;
    }
}
