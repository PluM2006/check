package ru.clevertec.app.service.impl;

import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.product.dbimpl.ProductRepositoryImpl;
import ru.clevertec.app.repository.product.mapper.ProductMapper;
import ru.clevertec.app.service.Service;

import java.util.Map;
import java.util.Optional;

public class ProductService implements Service<Product> {
    private final ProductMapper productMapper = new ProductMapper();
    @Override
    public Product add(Map<String, String> parameters) {
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        Product productFromParameters = productMapper.createProductFromParameters(parameters);
        return productRepository.add(productFromParameters);
    }

    @Override
    public Product update(Map<String, String> parameters) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public CustomList<Product> findAll() {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
