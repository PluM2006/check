package ru.clevertec.app.constant;

public enum Constants {
    CARD("card"),
    PRINT_TO("printTo"),
    TIME_FORMAT("HH:mm:ss"),
    DATE_FORMAT("dd-MM-YYYY"),
    ALL_DISCOUNT("10"),
    PRODUCT("product"),
    PRODUCT_FILE("productFile"),
    CARD_FILE("cardFile"),
    PATH_PRODUCT("product.csv"),
    PATH_CARD("card.csv"),
    INVALID_FILE_NAME("invalidDate.txt"),
    FILE_NAME("check.txt");

    private final String name;
    Constants(String name) {
        this.name = name;
    }
    public String  getName(){
        return this.name;
    }
}
