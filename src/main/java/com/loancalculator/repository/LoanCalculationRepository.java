package com.loancalculator.repository;

import com.loancalculator.model.entity.LoanCalculationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanCalculationRepository extends JpaRepository<LoanCalculationEntity, Long> {
}
