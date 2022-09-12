package ru.clevertec.app.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class CardDTO {

    private Long id;
    private String numberCard;
    private BigDecimal discount;
}
