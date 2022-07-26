package ru.clevertec.app.service.impl;

import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.Repository;
import ru.clevertec.app.repository.dbImpl.ProductRepositoryImpl;
import ru.clevertec.app.service.ArgsUtil;
import ru.clevertec.app.service.CheckFormatBuilder;
import ru.clevertec.app.service.interfaces.CheckItemsInterface;
import ru.clevertec.app.service.interfaces.CustomList;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CheckItemsDBImpl implements CheckItemsInterface {
    Repository<Product> repository = new ProductRepositoryImpl();
    CustomList<CheckItem> listCheckItem = new CustomArrayList<>();
    CustomList<Long> errorsItem = new CustomArrayList<>();

    @Override
    public CustomList<CheckItem> getCheckItem(String[] args) {
        Map<Long, Integer> mapCheckItems = ArgsUtil.getInstance(args).getMapCheckItems();
        for (var map: mapCheckItems.entrySet()){
            Optional<Product> productById = repository.findById(map.getKey());
            if (productById.isPresent()){
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
        CheckFormatBuilder.errorCheckItems(errorsItem);
        return listCheckItem;
    }
}
