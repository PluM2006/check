package ru.clevertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.app.entity.Shop;

import javax.transaction.Transactional;

@Transactional
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
