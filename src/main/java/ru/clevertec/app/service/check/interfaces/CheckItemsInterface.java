package ru.clevertec.app.service.check.interfaces;

import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.service.customlist.CustomList;

public interface CheckItemsInterface {
    CustomList<CheckItem> getCheckItem(String[] args);
}
