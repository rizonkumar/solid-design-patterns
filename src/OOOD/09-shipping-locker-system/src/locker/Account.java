package locker;

import java.math.BigDecimal;

public class Account {
    private final String accountId;
    private final String ownerName;
    // Policy defining locker usage rules for this account
    private final AccountLockerPolicy lockerPolicy;
    // Total charges accumulated for locker usage
    private BigDecimal usageCharges = new BigDecimal("0.00");

    // Creates a new account with specified details and policy
    public Account(String accountId, String ownerName, AccountLockerPolicy lockerPolicy) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.lockerPolicy = lockerPolicy;
    }

    // Adds a charge to the account's total usage charges
    public void addUsageCharge(BigDecimal amount) {
        usageCharges = usageCharges.add(amount);
    }

    public AccountLockerPolicy getLockerPolicy() {
        return lockerPolicy;
    }

    public BigDecimal getUsageCharges() {
        return usageCharges;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
