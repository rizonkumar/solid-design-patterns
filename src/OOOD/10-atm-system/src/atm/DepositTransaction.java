package atm;

import java.math.BigDecimal;

// Handles the deposit transaction process for adding funds to an account
public class DepositTransaction implements Transaction {
    final Account account;
    final BigDecimal amount;

    // Returns the transaction type as DEPOSIT
    @Override
    public TransactionType getType() {
        return TransactionType.DEPOSIT;
    }

    // Deposit transactions are always valid
    @Override
    public boolean validateTransaction() {
        return true;
    }

    public DepositTransaction(Account account, BigDecimal amount) {
        this.account = account;
        this.amount = amount;
    }

    // Executes the deposit by adding the amount to the account balance
    @Override
    public void executeTransaction() {
        account.updateBalanceWithTransaction(amount);
    }
}
