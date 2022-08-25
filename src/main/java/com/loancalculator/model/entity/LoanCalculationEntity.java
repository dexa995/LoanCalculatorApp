package com.loancalculator.model.entity;

import com.loancalculator.model.request.ImmutableLoanCalculation;
import com.loancalculator.model.request.LoanCalculation;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LOAN_CALCULATION")
@EntityListeners(AuditingEntityListener.class)
public class LoanCalculationEntity {
    @Id
    @SequenceGenerator(name = "LoanCalculationGen", sequenceName = "LoanCalculationGen", allocationSize = 1)
    @GeneratedValue(generator = "LoanCalculationGen", strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "AMOUNT",nullable = false)
    private Double amount;

    @Column(name = "ANNUAL_INTEREST_PERCENT",nullable = false)
    private Double annualInterestPercent;


    @Column(name = "NUMBER_OF_MONTHS",nullable = false)
    private Long numberOfMonths;

    @Column(name = "PAYMENT_PER_MONTH",nullable = false)
    private Double paymentPerMonth;

    @Column(name = "DATE_CREATED", nullable = false)
    @CreatedDate
    private LocalDateTime dateCreated;

    public LoanCalculationEntity() {
    }

    public LoanCalculationEntity(LoanCalculation loanCalculation) {
        this.amount = loanCalculation.getAmount();
        this.annualInterestPercent = loanCalculation.getAnnualInterestPercent();
        this.numberOfMonths = loanCalculation.getNumberOfMonths();
        this.paymentPerMonth = loanCalculation.getPaymentPerMonth();
    }


    public LoanCalculation getDto() {
        return ImmutableLoanCalculation.builder()
                .amount(amount)
                .annualInterestPercent(annualInterestPercent)
                .numberOfMonths(numberOfMonths)
                .build();
    }
}
