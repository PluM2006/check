package ru.clevertec.app.check.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.app.check.CheckItemsInterface;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.product.fileimpl.ProductFileCheckRepositoryImpl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckItemsFilesImpl implements CheckItemsInterface {

    private final ProductFileCheckRepositoryImpl productFileCheckRepository;

    @Override
    public CustomList<CheckItem> getCheckItem(Map<Long, Integer> mapCheckItems, CustomList<Long> errorsItem) {
        CustomList<CheckItem> listCheckItem = new CustomArrayList<>();
        CustomList<Product> allProduct = productFileCheckRepository.findAll();
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