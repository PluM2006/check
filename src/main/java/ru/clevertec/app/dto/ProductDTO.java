package ru.clevertec.app.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private Integer count;
    private Boolean sale;
}
