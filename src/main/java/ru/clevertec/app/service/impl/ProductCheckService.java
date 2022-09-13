package ru.clevertec.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.clevertec.app.customlist.CustomArrayList;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.dto.ProductDTO;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.mapper.ProductMapper;
import ru.clevertec.app.repository.ProductRepository;
import ru.clevertec.app.service.CheckService;
import ru.clevertec.app.validator.ValidationProduct;

import java.util.Optional;

import static ru.clevertec.app.constant.Constants.OFFSET_DEFAULT;
import static ru.clevertec.app.constant.Constants.PAGE_SIZE_DEFAULT;

@Service
@RequiredArgsConstructor
public class ProductCheckService implements CheckService<ProductDTO, Product> {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ValidationProduct validationProduct;


    public ProductDTO add(Product product) {
        if (!validationProduct.validParametersProduct(product)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return productMapper.toProductDTO(productRepository.save(product));
    }

    public ProductDTO update(Product product) {
        if (!validationProduct.validParametersProduct(product)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return productMapper.toProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO findById(String id) {
        if (!validationProduct.isValidIdProduct(id)) {
            //todo
            return null;
        }
        return productRepository.findById(Long.parseLong(id))
                .map(productMapper::toProductDTO)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public CustomList<ProductDTO> findAll() {
        CustomList<ProductDTO> productDTOs = new CustomArrayList<>();
        productRepository.findAll()
                .stream().map(productMapper::toProductDTO)
                .forEach(productDTOs::add);
        return productDTOs;
    }

    @Override
    public CustomList<ProductDTO> findAll(Pageable pageable) {
        CustomList<ProductDTO> productsDTOs = new CustomArrayList<>();
        productRepository.findAll(pageable)
                .map(productMapper::toProductDTO)
                .forEach(productsDTOs::add);
        return productsDTOs;
    }

    @Override
    public boolean delete(String id) {
        if (!validationProduct.isValidIdProduct(id)) {
            return false;
        }
        Optional<Product> product = productRepository.findById(Long.parseLong(id));
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return true;
        }
        return false;
    }
}
