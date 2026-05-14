package atm;

public class PinEntryState extends ATMState {

    private int attempts = 0;
    private final int MAX_ATTEMPTS = 3;

    @Override
    public void processPinEntry(ATMMachine atmMachine, String pin) {
        String cardNumber = atmMachine.getCardProcessor().getCardNumber();
        if (atmMachine.getBankInterface().checkPin(cardNumber, pin)) {
            atmMachine.getDisplay().showMessage("PIN verified. Please select a transaction.");
            atmMachine.transitionToState(new TransactionSelectionState());
        } else {
            attempts++;
            if (attempts >= MAX_ATTEMPTS) {
                atmMachine.getDisplay().showMessage("Too many invalid attempts. Card retained.");
                atmMachine.transitionToState(new IdleState());
            } else {
                atmMachine.getDisplay().showMessage("Invalid PIN. Please try again.");
            }
        }
    }

    @Override
    public void processCardEjection(ATMMachine atmMachine) {
        atmMachine.getDisplay().showMessage("Transaction cancelled, card ejected");
        atmMachine.getCardProcessor().ejectCard();
        atmMachine.transitionToState(new IdleState());
    }
}
