package ru.clevertec.app.entity;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class Cashier {

    private Long id;
    private String name;
    private String number;

    public Cashier(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
