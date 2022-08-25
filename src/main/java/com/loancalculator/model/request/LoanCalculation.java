package com.loancalculator.model.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value.Immutable
@JsonSerialize(as = ImmutableLoanCalculation.class)
@JsonDeserialize(as = ImmutableLoanCalculation.class)
public interface LoanCalculation {
    Double getAmount();

    Double getAnnualInterestPercent();

    Long getNumberOfMonths();

    @Value.Lazy
    default Double getPaymentPerMonth() {
        Double interestRatePerMonth = getAnnualInterestPercent() / 100 / 12;
        double pow = Math.pow((1 + interestRatePerMonth), getNumberOfMonths());
        double a = getAmount() * interestRatePerMonth * pow;
        double b = pow - 1;
        return BigDecimal.valueOf(a / b).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Value.Lazy
    default List<PaymentInstruction> getPaymentInstruction() {
       return IntStream.rangeClosed(1, getNumberOfMonths().intValue())
                .mapToObj(value -> ImmutablePaymentInstruction.builder()
                        .payment(getPaymentPerMonth())
                        .month(value)
                        .build())
                .collect(Collectors.toList());
    }

    @Value.Check
    default void check() {
        if (getNumberOfMonths() <= 0) {
            throw new IllegalArgumentException("Number of months must be greater then 0");
        }
        if (getAnnualInterestPercent() <= 0) {
            throw new IllegalArgumentException("Annual interest percent must be greater then 0");
        }
    }

    @Value.Immutable
    @JsonSerialize(as = ImmutablePaymentInstruction.class)
    @JsonDeserialize(as = ImmutablePaymentInstruction.class)
    interface PaymentInstruction {
        Integer getMonth();

        Double getPayment();
    }
}
