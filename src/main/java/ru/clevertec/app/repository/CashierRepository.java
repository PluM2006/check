package ru.clevertec.app.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.app.entity.Cashier;

@Transactional
public interface CashierRepository extends JpaRepository<Cashier, Long> {
}
