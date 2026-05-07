package vending;

public class MoneyInsertedState implements VendingMachineState {
    @Override
    public void insertMoney(VendingMachine VM, double amount) throws InvalidStateException {
        throw new InvalidStateException("Money already inserted. Please select a product.");
    }

    @Override
    public void selectProductByCode(VendingMachine VM, String productCode) throws InvalidStateException, InvalidTransactionException {
        VM.chooseProduct(productCode);
        VM.setState(new DispenseState());
    }

    @Override
    public void dispenseProduct(VendingMachine VM) throws InvalidStateException {
        throw new InvalidStateException("Cannot dispense without selecting a product first.");
    }

    @Override
    public String getStateDescription() {
        return "Money Inserted State - Please select a product";
    }
}
