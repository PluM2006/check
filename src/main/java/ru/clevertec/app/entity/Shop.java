package ru.clevertec.app.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "adress")
    private String address;

    public Shop(String name, String address) {
        this.name = name;
        this.address = address;
    }

}
