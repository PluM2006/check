package ru.clevertec.app.check;

import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.customlist.CustomList;

import java.util.Map;

public interface CheckItemsInterface {
    CustomList<CheckItem> getCheckItem(Map<Long, Integer> mapCheckItems, CustomList<Long> errorsItem);
}
