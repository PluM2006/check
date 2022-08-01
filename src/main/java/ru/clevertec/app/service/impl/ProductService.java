package ru.clevertec.app.service.impl;

import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.product.dbimpl.ProductRepositoryImpl;
import ru.clevertec.app.service.Service;
import ru.clevertec.app.validator.ValidationProduct;

import java.util.Optional;

import static ru.clevertec.app.constant.Constants.OFFSET_DEFAULT;
import static ru.clevertec.app.constant.Constants.PAGE_SIZE_DEFAULT;

public class ProductService implements Service<Product> {

    private static final ProductService INSTANCE = new ProductService();
    private final ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
    private final ValidationProduct validationProduct = new ValidationProduct();

    private ProductService() {

    }

    public static ProductService getInstance() {
        return INSTANCE;
    }

    public Optional<Product> add(Product product) {
        if (!validationProduct.validParametersProduct(product)) {
            return Optional.empty();
        }
        return Optional.of(productRepository.add(product));
    }

    public Optional<Product> update(Product product) {
        if (!validationProduct.validParametersProduct(product)) {
            return Optional.empty();
        }
        return Optional.of(productRepository.update(product));
    }

    @Override
    public Optional<Product> findById(String id) {
        if (!validationProduct.isValidIdProduct(id)) {
            return Optional.empty();
        }
        return productRepository.findById(Long.parseLong(id));
    }

    @Override
    public CustomList<Product> findAll(String limit, String offset) {
        if (limit == null) limit = PAGE_SIZE_DEFAULT;
        if (offset == null) offset = OFFSET_DEFAULT;

        return productRepository.findAll(Integer.parseInt(limit), Integer.parseInt(offset));
    }

    @Override
    public boolean delete(String id) {
        if (!validationProduct.isValidIdProduct(id)) {
            return false;
        }
        return productRepository.delete(Long.parseLong(id));
    }
}
