package ru.clevertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.app.entity.Cashier;

import javax.transaction.Transactional;

@Transactional
public interface CashierRepository extends JpaRepository<Cashier, Long> {
}
