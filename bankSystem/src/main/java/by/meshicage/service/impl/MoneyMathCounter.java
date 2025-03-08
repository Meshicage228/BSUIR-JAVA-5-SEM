package by.meshicage.service.impl;

import by.meshicage.service.abstr.TransactionTemplate;

import java.util.List;

public class MoneyMathCounter extends TransactionTemplate {
    public MoneyMathCounter(List<Double> amounts) {
        super(amounts);
    }

    @Override
    protected void calculateSum() {
        double sum = amounts.stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        System.out.println("Sum of amounts: " + sum);
    }

    @Override
    protected void findMax() {
        double max = amounts.stream()
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(0);

        System.out.println("Max amount: " + max);
    }
}