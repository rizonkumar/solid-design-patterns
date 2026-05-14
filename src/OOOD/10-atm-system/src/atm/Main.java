package atm;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ATM System Initialization ===");

        Bank bank = new Bank();
        bank.addAccount("123456", AccountType.CHECKING, "CARD-001", "1234");
        // Give some initial balance via a direct internal transaction
        Account account = bank.getAccountByAccountNumber("123456");
        account.updateBalanceWithTransaction(new BigDecimal("500.00"));

        // Dummy hardware components
        Display display = message -> System.out.println("[DISPLAY] " + message);
        Keypad keypad = () -> "1234";
        CardProcessor cardProcessor = new CardProcessor() {
            private String card;
            public void setCardNumber(String c) { this.card = c; }
            public String getCardNumber() { return card; }
            public void ejectCard() { System.out.println("[CARD PROCESSOR] Card Ejected"); this.card = null; }
        };
        CashDispenser cashDispenser = amount -> System.out.println("[DISPENSER] Dispensing $" + amount);
        DepositBox depositBox = amount -> System.out.println("[DEPOSIT BOX] Accepted $" + amount);

        ATMMachine atm = new ATMMachine(bank, cardProcessor, depositBox, cashDispenser, keypad, display);

        System.out.println("\n--- User Inserts Card ---");
        atm.insertCard("CARD-001");

        System.out.println("\n--- User Enters PIN ---");
        atm.enterPin("1234");

        System.out.println("\n--- User Requests Withdrawal ---");
        atm.withdrawRequest();

        System.out.println("\n--- User Enters Amount ---");
        atm.enterAmount(new BigDecimal("50.00"));

        System.out.println("\n--- User Ends Session ---");
        atm.ejectCard();
        
        System.out.println("\n--- Final Account Balance ---");
        System.out.println("$" + account.getBalance());
    }
}
