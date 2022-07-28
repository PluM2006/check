package ru.clevertec.app.check;

import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.customlist.CustomList;

public interface CheckItemsInterface {
    CustomList<CheckItem> getCheckItem(String[] args);
}
