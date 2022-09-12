package ru.clevertec.app.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.dto.ProductDTO;
import ru.clevertec.app.entity.Product;
import ru.clevertec.app.service.CheckService;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CheckService<ProductDTO, Product> productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getCard(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<CustomList<ProductDTO>> getAllCard(Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveCard(@RequestBody Product product) {
        return ResponseEntity.ok(productService.add(product));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody Product product) {
        return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        boolean delete = productService.delete(id);
        return ResponseEntity.ok(delete);
    }


}
