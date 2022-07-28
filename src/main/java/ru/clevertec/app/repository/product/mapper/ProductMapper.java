package ru.clevertec.app.repository.product.mapper;

import ru.clevertec.app.entity.Product;

import java.math.BigDecimal;
import java.util.Map;

public class ProductMapper {

    public Product createProductFromParameters(Map<String, String> parameters) {
        Product product = new Product();
        product.setName(parameters.get("name"));
        product.setPrice(new BigDecimal(parameters.get("price")));
        product.setCount(Integer.parseInt(parameters.get("count")));
        product.setSale(Boolean.parseBoolean(parameters.get("sale")));
        return product;
    }
}
