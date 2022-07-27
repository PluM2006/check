package ru.clevertec.app.constant;

public enum Constants {
    CARD("card"),
    PRINT_TO("printTo");

    private final String name;

    Constants(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
