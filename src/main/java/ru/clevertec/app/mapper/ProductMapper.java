package ru.clevertec.app.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.app.dto.ProductDTO;
import ru.clevertec.app.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toProductDTO(Product product);
}
