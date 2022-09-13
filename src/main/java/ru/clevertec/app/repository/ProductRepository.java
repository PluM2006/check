package ru.clevertec.app.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.app.entity.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
}
