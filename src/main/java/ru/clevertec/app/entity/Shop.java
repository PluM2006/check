package ru.clevertec.app.entity;

import java.util.Objects;

public class Shop {

    private Long id;
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

    public Shop() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return id.equals(shop.id) && name.equals(shop.name) && address.equals(shop.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @Override
    public String
    toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
