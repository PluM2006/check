package ru.clevertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.app.entity.Product;

import javax.transaction.Transactional;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
}
