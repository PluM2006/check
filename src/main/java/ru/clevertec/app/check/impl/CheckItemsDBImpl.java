package ru.clevertec.app.check.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.app.check.CheckItemsInterface;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.dto.ProductDTO;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.CheckService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckItemsDBImpl implements CheckItemsInterface {

    private final CheckService<ProductDTO, Product> productCheckService;

    @Override
    public CustomList<CheckItem> getCheckItem(Map<Long, Integer> mapCheckItems, CustomList<Long> errorsItem) {
        CustomList<CheckItem> listCheckItem = new CustomArrayList<>();
        for (var map : mapCheckItems.entrySet()) {
            ProductDTO productById = productCheckService.findById(map.getKey().toString());

                CheckItem checkItem = new CheckItem(
                        productById,
                        map.getValue(),
                        productById.getPrice().multiply(new BigDecimal(map.getValue())),
                        BigDecimal.ZERO,
                        false);
                listCheckItem.add(checkItem);

//            if (productById.isPresent()) {
//                CheckItem checkItem = new CheckItem(
//                        productById.get(),
//                        map.getValue(),
//                        productById.get().getPrice().multiply(new BigDecimal(map.getValue())),
//                        BigDecimal.ZERO,
//                        false);
//                listCheckItem.add(checkItem);
//            } else {
//                errorsItem.add(map.getKey());
//            }
        }
        return listCheckItem;
    }
}
