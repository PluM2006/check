package ru.clevertec.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.CheckRepository;
import ru.clevertec.app.service.CheckService;
import ru.clevertec.app.validator.ValidationProduct;

import java.util.Optional;

import static ru.clevertec.app.constant.Constants.OFFSET_DEFAULT;
import static ru.clevertec.app.constant.Constants.PAGE_SIZE_DEFAULT;

@Service
@RequiredArgsConstructor
public class ProductCheckService implements CheckService<Product> {

    private final CheckRepository<Product> productCheckRepositoryImpl;
    private final ValidationProduct validationProduct;


    public Optional<Product> add(Product product) {
        if (!validationProduct.validParametersProduct(product)) {
            return Optional.empty();
        }
        return Optional.of(productCheckRepositoryImpl.add(product));
    }

    public Optional<Product> update(Product product) {
        if (!validationProduct.validParametersProduct(product)) {
            return Optional.empty();
        }
        return Optional.of(productCheckRepositoryImpl.update(product));
    }

    @Override
    public Optional<Product> findById(String id) {
        if (!validationProduct.isValidIdProduct(id)) {
            return Optional.empty();
        }
        return productCheckRepositoryImpl.findById(Long.parseLong(id));
    }

    @Override
    public CustomList<Product> findAll(String limit, String offset) {
        if (limit == null) limit = PAGE_SIZE_DEFAULT;
        if (offset == null) offset = OFFSET_DEFAULT;
        return productCheckRepositoryImpl.findAll(Integer.parseInt(limit), Integer.parseInt(offset));
    }

    @Override
    public boolean delete(String id) {
        if (!validationProduct.isValidIdProduct(id)) {
            return false;
        }
        return productCheckRepositoryImpl.delete(Long.parseLong(id));
    }
}
