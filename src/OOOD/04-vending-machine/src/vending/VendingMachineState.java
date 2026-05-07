package vending;

public interface VendingMachineState {
    // Handles money insertion in the current state
    void insertMoney(VendingMachine VM, double amount) throws InvalidStateException;

    // Handles product selection in the current state
    void selectProductByCode(VendingMachine VM, String productCode) throws InvalidStateException, InvalidTransactionException;

    // Handles product dispensing in the current state
    void dispenseProduct(VendingMachine VM) throws InvalidStateException, InvalidTransactionException;

    // Returns a description of the current state
    String getStateDescription();
}