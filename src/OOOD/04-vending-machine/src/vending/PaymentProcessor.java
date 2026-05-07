package vending;

import java.math.BigDecimal;

public class PaymentProcessor {
    // Tracks the current balance in the payment processor
    private BigDecimal currentBalance = BigDecimal.ZERO;

    // Adds the specified amount to the current balance
    public void addBalance(BigDecimal amount) {
        currentBalance = currentBalance.add(amount);
    }

    // Deducts the specified amount from the current balance
    public void charge(BigDecimal amount) {
        currentBalance = currentBalance.subtract(amount);
    }

    // Returns the current balance as change and resets the balance to zero
    public BigDecimal returnChange() {
        BigDecimal change = currentBalance;
        currentBalance = BigDecimal.ZERO;
        return change;
    }

    // Returns the current balance
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }
}