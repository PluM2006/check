package ru.clevertec.app.utils;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YamlProperties {

    private DataBaseProperties database;
    private ConstantsProperties constants;
    private HibernateProperties hibernate;

}
