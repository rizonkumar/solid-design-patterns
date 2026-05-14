package atm;

import java.math.BigDecimal;

public interface BankInterface {
    void addAccount(String accountNumber, AccountType type, String cardNumber, String pin);

    boolean validateCard(String cardNumber);

    boolean checkPin(String cardNumber, String pinNumber);

    Account getAccountByAccountNumber(String accountNumber);

    Account getAccountByCard(String cardNumber);

    boolean withdrawFunds(Account account, BigDecimal amount);
}
