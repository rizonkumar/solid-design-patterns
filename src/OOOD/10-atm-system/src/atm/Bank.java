package atm;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

// Manages bank accounts and provides banking operations like validation and transactions
public class Bank implements BankInterface {
    private final Map<String, Account> accounts = new HashMap<>();
    private final Map<String, Account> accountByCard = new HashMap<>();

    // Creates a new account and stores it in both account and card maps
    @Override
    public void addAccount(
            final String accountNumber,
            final AccountType type,
            final String cardNumber,
            final String pin) {
        final Account newAccount = new Account(accountNumber, type, cardNumber, pin);
        accounts.put(newAccount.getAccountNumber(), newAccount);
        accountByCard.put(newAccount.getCardNumber(), newAccount);
    }

    // Checks if a card number exists in the bank's records
    @Override
    public boolean validateCard(final String cardNumber) {
        return getAccountByCard(cardNumber) != null;
    }

    // Verifies if the provided PIN matches the card's stored PIN
    @Override
    public boolean checkPin(String cardNumber, String pinNumber) {
        Account account = getAccountByCard(cardNumber);
        if (account != null) {
            return account.validatePin(pinNumber);
        }
        return false;
    }

    // Retrieves account by account number
    @Override
    public Account getAccountByAccountNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }

    // Retrieves account by card number
    @Override
    public Account getAccountByCard(String cardNumber) {
        return accountByCard.get(cardNumber);
    }

    // Attempts to withdraw specified amount from account if sufficient funds exist
    @Override
    public boolean withdrawFunds(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) >= 0) {
            account.updateBalanceWithTransaction(amount.negate());
            return true;
        }
        return false;
    }
}
