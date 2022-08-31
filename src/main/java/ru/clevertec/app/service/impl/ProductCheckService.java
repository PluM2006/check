package ru.clevertec.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.repository.ProductRepository;
import ru.clevertec.app.service.CheckService;
import ru.clevertec.app.validator.ValidationProduct;

import java.util.Optional;

import static ru.clevertec.app.constant.Constants.OFFSET_DEFAULT;
import static ru.clevertec.app.constant.Constants.PAGE_SIZE_DEFAULT;

@Service
@RequiredArgsConstructor
public class ProductCheckService implements CheckService<Product> {

    private final ProductRepository productRepository;
    private final ValidationProduct validationProduct;


    public Optional<Product> add(Product product) {
        if (!validationProduct.validParametersProduct(product)) {
            return Optional.empty();
        }
        return Optional.of(productRepository.save(product));
    }

    public Optional<Product> update(Product product) {
        if (!validationProduct.validParametersProduct(product)) {
            return Optional.empty();
        }
        return Optional.of(productRepository.save(product));
    }

    @Override
    public Optional<Product> findById(String id) {
        if (!validationProduct.isValidIdProduct(id)) {
            return Optional.empty();
        }
        return productRepository.findById(Long.parseLong(id));
    }

    @Override
    public CustomList<Product> findAll() {
        CustomList<Product> products = new CustomArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public CustomList<Product> findAll(String limit, String offset) {
        CustomList<Product> products = new CustomArrayList<>();
        if (limit == null) limit = PAGE_SIZE_DEFAULT;
        if (offset == null) offset = OFFSET_DEFAULT;
        productRepository.findAll(PageRequest.of(Integer.parseInt(offset), Integer.parseInt(limit)))
                .forEach(products::add);
        return products;
    }

    @Override
    public boolean delete(String id) {
        if (!validationProduct.isValidIdProduct(id)) {
            return false;
        }
        Optional<Product> product = findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return true;
        }
        return false;
    }
}
