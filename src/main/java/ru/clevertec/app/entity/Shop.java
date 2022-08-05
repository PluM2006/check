package ru.clevertec.app.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Shop {

    private @Id Long id;
    private String name;
    private String address;

    public Shop(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Shop(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
