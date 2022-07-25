package ru.clevertec.app.service.interfaces;

import ru.clevertec.app.entity.CheckItem;

public interface CheckItemsInterface {
    CustomList<CheckItem> getCheckItem(String[] args);
}
