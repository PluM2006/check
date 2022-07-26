package ru.clevertec.app.constant;

public enum Constants {
    CARD("card"),
    PRINT_TO("printTo"),
    PATH_PRODUCT_FILE("productFile"),
    PATH_CARD_FILE("cardFile") ;



    private final String name;

    Constants(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
