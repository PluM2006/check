package ru.clevertec.app.check.impl;

import ru.clevertec.app.check.CheckItemsInterface;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.product.fileimpl.ProductFileRepositoryImpl;
import ru.clevertec.app.utils.CheckStringFormatting;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CheckItemsFilesImpl implements CheckItemsInterface {

    Repository<Product> repository = new ProductFileRepositoryImpl();
    CustomList<CheckItem> listCheckItem = new CustomArrayList<>();

    @Override
    public CustomList<CheckItem> getCheckItem(Map<Long, Integer> mapCheckItems, CustomList<Long> errorsItem) {
        CustomList<Product> allProduct = repository.findAll(null, null);
        for (var map : mapCheckItems.entrySet()) {
            Optional<Product> product = allProduct
                    .stream()
                    .filter(p -> p.getId().equals(map.getKey()))
                    .findAny();
            if (product.isPresent()) {
                CheckItem checkItem = new CheckItem(product.get(), map.getValue(), product
                        .get()
                        .getPrice()
                        .multiply(new BigDecimal(map.getValue())), BigDecimal.ZERO, false);
                listCheckItem.add(checkItem);
            } else {
                errorsItem.add(map.getKey());
            }
        }

        return listCheckItem;
    }
}
