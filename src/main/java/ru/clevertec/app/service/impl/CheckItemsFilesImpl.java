package ru.clevertec.app.service.impl;

import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.fileImpl.ProductFileRepositoryImpl;
import ru.clevertec.app.service.utils.CheckFormatBuilder;
import ru.clevertec.app.service.utils.ArgsUtil;
import ru.clevertec.app.service.interfaces.CustomList;
import ru.clevertec.app.service.interfaces.CheckItemsInterface;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CheckItemsFilesImpl implements CheckItemsInterface {

    Repository<Product> repository = new ProductFileRepositoryImpl();
    CustomList<CheckItem> listCheckItem = new CustomArrayList<>();
    CustomList<Long> errorsItem = new CustomArrayList<>();

    @Override
    public CustomList<CheckItem> getCheckItem(String[] args) {
        CustomList<Product> allProduct = repository.findAll();
        Map<Long, Integer> mapCheckItems = ArgsUtil.getInstance(args).getMapCheckItems();
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
        CheckFormatBuilder.errorCheckItems(errorsItem);
        return listCheckItem;
    }
}
