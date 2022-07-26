package ru.clevertec.app.constant;

public enum Constants {
    CARD("card"),
    PRINT_TO("printTo"),
    PATH_PRODUCT_FILE("productFile"),
    PATH_CARD_FILE("cardFile"),
    PATH_PRODUCT("product.csv"),
    PATH_CARD("card.csv"),
    INVALID_FILE_NAME("invalidDate.txt"),
    FILE_NAME("check.txt"),
    ALL_DISCOUNT("10")
    ;



    private final String name;

    Constants(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
