package com.loancalculator;

import com.loancalculator.model.request.ImmutableLoanCalculation;
import com.loancalculator.model.request.LoanCalculation;
import com.loancalculator.repository.LoanCalculationRepository;
import com.loancalculator.service.LoanCalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoanCalculatorTest {
    @Autowired
    private LoanCalculationService loanCalculationService;

    @Autowired
    private LoanCalculationRepository loanCalculationRepository;

    @Test
    void calculateTest() {
        LoanCalculation TEST_DATA = ImmutableLoanCalculation.builder()
                .amount(20000D)
                .numberOfMounts(60L)
                .annualInterestPercent(5d)
                .build();

        int currentNumberOfEntities = loanCalculationRepository.findAll().size();

        LoanCalculation savedCalculation = loanCalculationService.save(TEST_DATA);

        Assertions.assertEquals(savedCalculation.getPaymentPerMount(),377.42d);
        Assertions.assertEquals(currentNumberOfEntities+1,loanCalculationRepository.findAll().size());
    }

}
