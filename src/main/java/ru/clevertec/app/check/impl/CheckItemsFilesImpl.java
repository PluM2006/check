package ru.clevertec.app.check.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.clevertec.app.check.CheckItemsInterface;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.dto.ProductDTO;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.mapper.ProductMapper;
import ru.clevertec.app.repository.FileRepository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CheckItemsFilesImpl implements CheckItemsInterface {

    private final FileRepository<Product> productFileCheckRepository;
    private final ProductMapper productMapper;

    @Override
    public CustomList<CheckItem> getCheckItem(Map<Long, Integer> mapCheckItems, CustomList<Long> errorsItem) {
        CustomList<CheckItem> listCheckItem = new CustomArrayList<>();
        CustomList<Product> allProduct = productFileCheckRepository.findAll();
        for (var map : mapCheckItems.entrySet()) {
            Optional<ProductDTO> product = allProduct
                    .stream()
                    .filter(p -> p.getId().equals(map.getKey()))
                    .map(productMapper::toProductDTO)
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