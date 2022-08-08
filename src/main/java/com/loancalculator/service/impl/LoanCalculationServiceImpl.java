package com.loancalculator.service.impl;

import com.loancalculator.model.entity.LoanCalculationEntity;
import com.loancalculator.model.request.LoanCalculation;
import com.loancalculator.repository.LoanCalculationRepository;
import com.loancalculator.service.LoanCalculationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoanCalculationServiceImpl implements LoanCalculationService {
    private final LoanCalculationRepository loanCalculationRepository;

    public LoanCalculationServiceImpl(LoanCalculationRepository loanCalculationRepository) {
        this.loanCalculationRepository = loanCalculationRepository;
    }

    @Override
    public LoanCalculation save(LoanCalculation loanCalculatorRequest) {
        return loanCalculationRepository.save(new LoanCalculationEntity(loanCalculatorRequest)).getDto();
    }

    @Override
    public LoanCalculation findById(Long id) {
        return loanCalculationRepository.findById(id)
                .map(LoanCalculationEntity::getDto)
                .orElseThrow(() -> new RuntimeException("Entity with given id is not found"));
    }

    @Override
    public List<LoanCalculation> getAll() {
        return loanCalculationRepository.findAll().stream()
                .map(LoanCalculationEntity::getDto)
                .collect(Collectors.toList());
    }
}
