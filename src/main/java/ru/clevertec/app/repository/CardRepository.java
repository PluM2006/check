package ru.clevertec.app.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.app.entity.Card;

@Transactional
public interface CardRepository extends JpaRepository<Card, Long> {
}
