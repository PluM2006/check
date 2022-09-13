package ru.clevertec.app.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.app.entity.Shop;

@Transactional
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
