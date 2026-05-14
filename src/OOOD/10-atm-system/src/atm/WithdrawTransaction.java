package atm;

import java.math.BigDecimal;

// Handles the withdrawal transaction process for removing funds from an account
public class WithdrawTransaction implements Transaction {
    Account account;
    BigDecimal amount;

    // Returns the transaction type as WITHDRAW
    @Override
    public TransactionType getType() {
        return TransactionType.WITHDRAW;
    }

    // Validates if the account has sufficient funds for withdrawal
    @Override
    public boolean validateTransaction() {
        assert account != null;
        return account.getBalance().compareTo(amount) > 0;
    }

    // Creates a new withdrawal transaction, throws exception if validation fails
    public WithdrawTransaction(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
        if (!validateTransaction()) {
            throw new IllegalStateException(
                    "Cannot complete withdrawal: Insufficient funds in account");
        }
    }

    // Executes the withdrawal by subtracting the amount from account balance
    @Override
    public void executeTransaction() {
        account.updateBalanceWithTransaction(amount.negate());
    }
}
