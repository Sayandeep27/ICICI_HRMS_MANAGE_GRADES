package com.hrms.gradeband.repository;

import com.hrms.gradeband.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}