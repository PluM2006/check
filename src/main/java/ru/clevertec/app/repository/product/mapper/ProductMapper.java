package ru.clevertec.app.repository.product.mapper;

import ru.clevertec.app.constant.ParametersNames;
import ru.clevertec.app.entity.Product;

import java.math.BigDecimal;
import java.util.Map;

public class ProductMapper {

    private final Map<String, String> parameters;

    public ProductMapper(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public Product createProductFromParameters() {
        Product product = new Product();
        product.setName(parameters.get(ParametersNames.PRODUCT_NAME));
        product.setPrice(new BigDecimal(parameters.get(ParametersNames.PRODUCT_PRICE).replace(",", ".")));
        product.setCount(Integer.parseInt(parameters.get(ParametersNames.PRODUCT_COUNT)));
        product.setSale(Boolean.parseBoolean(parameters.get(ParametersNames.PRODUCT_SALE)));
        return product;
    }
}
