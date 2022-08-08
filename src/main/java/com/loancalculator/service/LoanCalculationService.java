package com.loancalculator.service;

import com.loancalculator.model.request.LoanCalculation;

import java.util.List;

public interface LoanCalculationService {
    LoanCalculation save(LoanCalculation loanCalculatorRequest);
    LoanCalculation findById(Long id);
    List<LoanCalculation> getAll();
}
