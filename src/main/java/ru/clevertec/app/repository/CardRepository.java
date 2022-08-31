package ru.clevertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.app.entity.Card;

import javax.transaction.Transactional;

@Transactional
public interface CardRepository extends JpaRepository<Card, Long> {
}
