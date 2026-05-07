package vending;

public class DispenseState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachine VM, double amount) throws InvalidStateException {
        throw new InvalidStateException("Currently dispensing. Please wait.");
    }

    @Override
    public void selectProductByCode(VendingMachine VM, String productCode) throws InvalidStateException {
        throw new InvalidStateException("Already processing a product.");
    }

    @Override
    public void dispenseProduct(VendingMachine VM) throws InvalidStateException, InvalidTransactionException {
        VM.confirmTransaction();
        VM.setState(new NoMoneyInsertedState());
    }

    @Override
    public String getStateDescription() {
        return "Dispense State - Dispensing product...";
    }
}
