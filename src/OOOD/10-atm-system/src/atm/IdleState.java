package atm;

public class IdleState extends ATMState {
    /**
     * This method is called when a card is inserted into the ATM. This transitions the ATM to the
     * PinEntryState if the card is valid.
     */
    @Override
    public void processCardInsertion(ATMMachine atmMachine, String cardNumber) {
        if (atmMachine.getBankInterface().validateCard(cardNumber)) {
            atmMachine.getCardProcessor().setCardNumber(cardNumber);
            atmMachine.getDisplay().showMessage("Please enter your PIN");
            atmMachine.transitionToState(new PinEntryState());
        } else {
            atmMachine.getDisplay().showMessage("Invalid card. Please try again.");
        }
    }
}
