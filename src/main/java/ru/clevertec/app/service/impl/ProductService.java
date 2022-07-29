package ru.clevertec.app.service.impl;

import ru.clevertec.app.controller.ParameterNames;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.product.dbimpl.ProductRepositoryImpl;
import ru.clevertec.app.repository.product.mapper.ProductMapper;
import ru.clevertec.app.service.Service;
import ru.clevertec.app.validator.ValidationProduct;

import java.util.Map;
import java.util.Optional;

public class ProductService implements Service<Product> {
    private final ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
    private final ValidationProduct validationProduct = new ValidationProduct();

    @Override
    public Optional<Product> add(Map<String, String> parameters) {
        if (!validationProduct.validParametersProduct(parameters)) {
            return Optional.empty();
        }
        ProductMapper productMapper = new ProductMapper(parameters);
        Product productFromParameters = productMapper.createProductFromParameters();
        return Optional.of(productRepository.add(productFromParameters));
    }

    @Override
    public Optional<Product> update(Map<String, String> parameters) {
        String id = parameters.get(ParameterNames.PRODUCT_ID);
        if (!validationProduct.validParametersProduct(parameters) &&
                validationProduct.isValidIdProduct(id)) {
            return Optional.empty();
        }
        ProductMapper productMapper = new ProductMapper(parameters);
        Product productFromParameters = productMapper.createProductFromParameters();
        productFromParameters.setId(Long.parseLong(id));
        return Optional.of(productRepository.update(productFromParameters));
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
        if (limit == null) limit = "20";
        if (offset == null) offset = "0";

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
