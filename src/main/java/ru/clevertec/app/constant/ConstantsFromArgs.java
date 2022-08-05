package ru.clevertec.app.constant;

public enum ConstantsFromArgs {

    CARD("card"),
    PRINT_TO("printTo");

    private final String name;

    ConstantsFromArgs(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
