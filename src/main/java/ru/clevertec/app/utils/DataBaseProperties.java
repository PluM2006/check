package ru.clevertec.app.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataBaseProperties {

    private String driver;
    private String url;
    private String user;
    private String password;
    private String pollSize;

}
