package atm;

public class TransactionSelectionState extends ATMState {

    @Override
    public void processWithdrawalRequest(ATMMachine atmMachine) {
        atmMachine.getDisplay().showMessage("Please enter withdrawal amount.");
        atmMachine.transitionToState(new WithdrawAmountEntryState());
    }

    @Override
    public void processDepositRequest(ATMMachine atmMachine) {
        atmMachine.getDisplay().showMessage("Please insert cash into the deposit box.");
        atmMachine.transitionToState(new DepositCollectionState());
    }

    @Override
    public void processCardEjection(ATMMachine atmMachine) {
        atmMachine.getDisplay().showMessage("Transaction cancelled, card ejected");
        atmMachine.getCardProcessor().ejectCard();
        atmMachine.transitionToState(new IdleState());
    }
}
