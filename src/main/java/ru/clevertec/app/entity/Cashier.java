package ru.clevertec.app.entity;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Cashier {

    private Long id;
    private String name;
    private String number;

    public Cashier(Long id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public Cashier(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
