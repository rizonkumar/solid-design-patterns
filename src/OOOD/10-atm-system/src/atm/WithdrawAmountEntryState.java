package atm;

import java.math.BigDecimal;

public class WithdrawAmountEntryState extends ATMState {
    // Handles card ejection by canceling transaction and returning to idle state
    @Override
    public void processCardEjection(ATMMachine atmMachine) {
        atmMachine.getDisplay().showMessage("Transaction cancelled, card ejected");
        atmMachine.getCardProcessor().ejectCard();
        atmMachine.transitionToState(new IdleState());
    }

    // Processes withdrawal request by checking balance and dispensing cash if sufficient funds
    @Override
    public void processAmountEntry(ATMMachine atmMachine, BigDecimal amount) {
        String cardNumber = atmMachine.getCardProcessor().getCardNumber();
        Account account = atmMachine.getBankInterface().getAccountByCard(cardNumber);
        boolean isSuccess = atmMachine.getBankInterface().withdrawFunds(account, amount);

        if (isSuccess) {
            atmMachine.getCashDispenser().dispenseCash(amount);
            atmMachine.getDisplay().showMessage("Please take your cash.");
        } else {
            atmMachine.getDisplay().showMessage("Insufficient funds, please try again.");
        }
        atmMachine.transitionToState(new TransactionSelectionState());
    }
}
