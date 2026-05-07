package vending;

public class NoMoneyInsertedState implements VendingMachineState {
    // Adds money to the machine and transitions to MoneyInsertedState
    @Override
    public void insertMoney(VendingMachine VM, double amount) {
        VM.addBalance(amount);
        VM.setState(new MoneyInsertedState());
    }

    // Throws exception as product selection is not allowed without money
    @Override
    public void selectProductByCode(VendingMachine VM, String productCode)
            throws InvalidStateException {
        throw new InvalidStateException("Cannot select a product without inserting money.");
    }

    // Throws exception as product dispensing is not allowed without money
    @Override
    public void dispenseProduct(VendingMachine VM) throws InvalidStateException {
        throw new InvalidStateException("Cannot dispense product without inserting money.");
    }

    // Returns a description of the current state
    @Override
    public String getStateDescription() {
        return "No Money Inserted State - Please insert money to proceed";
    }
}