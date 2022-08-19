package ru.clevertec.app.check.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.app.annatation.Log;
import ru.clevertec.app.check.CheckItemsInterface;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.CheckRepository;
import ru.clevertec.app.repository.product.dbimpl.ProductCheckRepositoryImpl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckItemsDBImpl implements CheckItemsInterface {

    private final CheckRepository<Product> productCheckRepositoryImpl;

    @Override
    public CustomList<CheckItem> getCheckItem(Map<Long, Integer> mapCheckItems, CustomList<Long> errorsItem) {
        CustomList<CheckItem> listCheckItem = new CustomArrayList<>();
        for (var map : mapCheckItems.entrySet()) {
            Optional<Product> productById = productCheckRepositoryImpl.findById(map.getKey());
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
