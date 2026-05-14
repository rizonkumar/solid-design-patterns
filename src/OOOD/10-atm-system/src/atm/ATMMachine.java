package atm;

import java.math.BigDecimal;

// Main ATM machine class that manages the state and hardware components of the ATM
public class ATMMachine {
    private ATMState state;

    private final CardProcessor cardProcessor;
    private final DepositBox depositBox;
    private final CashDispenser cashDispenser;
    private final Keypad keypad;
    private final Display display;

    private final BankInterface bank;

    // Initializes ATM with all required hardware components and bank interface
    public ATMMachine(
            BankInterface bank,
            CardProcessor cardProcessor,
            DepositBox depositBox,
            CashDispenser cashDispenser,
            Keypad keypad,
            Display display) {
        this.bank = bank;
        this.cardProcessor = cardProcessor;
        this.depositBox = depositBox;
        this.cashDispenser = cashDispenser;
        this.keypad = keypad;
        this.display = display;
        this.state = new IdleState();
    }

    // Forwards card insertion to current state for processing
    public void insertCard(String cardNumber) {
        state.processCardInsertion(this, cardNumber);
    }

    // Forwards card ejection to current state for processing
    public void ejectCard() {
        state.processCardEjection(this);
    }

    // Forwards PIN entry to current state for validation
    public void enterPin(String pin) {
        state.processPinEntry(this, pin);
    }

    // Forwards withdrawal request to current state for processing
    public void withdrawRequest() {
        state.processWithdrawalRequest(this);
    }

    // Forwards deposit request to current state for processing
    public void depositRequest() {
        state.processDepositRequest(this);
    }

    // Forwards amount entry to current state for processing
    public void enterAmount(BigDecimal amount) {
        state.processAmountEntry(this, amount);
    }

    // Forwards deposit collection to current state for processing
    public void collectDeposit(BigDecimal amount) {
        state.processDepositCollection(this, amount);
    }

    // Returns the display component for showing messages
    public Display getDisplay() {
        return display;
    }

    // Returns the cash dispenser component for handling withdrawals
    public CashDispenser getCashDispenser() {
        return cashDispenser;
    }

    // Returns the bank interface for account operations
    public BankInterface getBankInterface() {
        return bank;
    }

    // Returns the card processor component for handling card operations
    public CardProcessor getCardProcessor() {
        return cardProcessor;
    }

    // Returns the keypad component for user input
    public Keypad getKeypad() {
        return keypad;
    }

    // Updates the current state of the ATM
    public void transitionToState(ATMState nextState) {
        this.state = nextState;
    }

    // Returns the current state of the ATM
    public ATMState getCurrentState() {
        return state;
    }

    // Returns the deposit box component for handling deposits
    public DepositBox getDepositBox() {
        return depositBox;
    }
}
