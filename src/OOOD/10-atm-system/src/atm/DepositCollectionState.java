package atm;

import java.math.BigDecimal;

public class DepositCollectionState extends ATMState {

    @Override
    public void processDepositCollection(ATMMachine atmMachine, BigDecimal amount) {
        String cardNumber = atmMachine.getCardProcessor().getCardNumber();
        Account account = atmMachine.getBankInterface().getAccountByCard(cardNumber);
        
        atmMachine.getDepositBox().acceptDeposit(amount);
        
        DepositTransaction transaction = new DepositTransaction(account, amount);
        transaction.executeTransaction();
        
        atmMachine.getDisplay().showMessage("Deposit successful. Your new balance is: " + account.getBalance());
        atmMachine.transitionToState(new TransactionSelectionState());
    }

    @Override
    public void processCardEjection(ATMMachine atmMachine) {
        atmMachine.getDisplay().showMessage("Transaction cancelled, card ejected");
        atmMachine.getCardProcessor().ejectCard();
        atmMachine.transitionToState(new IdleState());
    }
}
