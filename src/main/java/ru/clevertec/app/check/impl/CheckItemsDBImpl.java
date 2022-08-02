package ru.clevertec.app.check.impl;

import ru.clevertec.app.check.CheckItemsInterface;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.product.dbimpl.ProductRepositoryImpl;
import ru.clevertec.app.utils.CheckStringFormatting;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CheckItemsDBImpl implements CheckItemsInterface {

    private static final CheckItemsDBImpl INSTANCE = new CheckItemsDBImpl();
    private final Repository<Product> repository = new ProductRepositoryImpl();

    private CheckItemsDBImpl() {
    }

    public static CheckItemsDBImpl getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public CustomList<CheckItem> getCheckItem(Map<Long, Integer> mapCheckItems, CustomList<Long> errorsItem) {
        CustomList<CheckItem> listCheckItem = new CustomArrayList<>();
        for (var map : mapCheckItems.entrySet()) {
            Optional<Product> productById = repository.findById(map.getKey());
            if (productById.isPresent()) {
                CheckItem checkItem = new CheckItem(
                        productById.get(),
                        map.getValue(),
                        productById.get().getPrice().multiply(new BigDecimal(map.getValue())),
                        BigDecimal.ZERO,
                        false);
                listCheckItem.add(checkItem);
            } else {
                errorsItem.add(map.getKey());
            }
        }
        return listCheckItem;
    }
}
