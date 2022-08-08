package com.loancalculator.controller;

import com.loancalculator.model.request.LoanCalculation;
import com.loancalculator.service.LoanCalculationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan")
public class LoanCalculatorController {
    private final LoanCalculationService loanCalculationService;

    public LoanCalculatorController(LoanCalculationService loanCalculationService) {
        this.loanCalculationService = loanCalculationService;
    }

    @PostMapping("/calculate")
    public List<LoanCalculation.PaymentInstruction> calculator(@RequestBody LoanCalculation loanCalculatorRequest) {
        return loanCalculationService.save(loanCalculatorRequest).getPaymentInstruction();
    }
}
