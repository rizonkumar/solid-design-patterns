package vending;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

class VendingMachine {
    // Stores the history of all completed transactions
    private final List<Transaction> transactionHistory;
    // Manages the inventory of products in the vending machine
    private final InventoryManager inventoryManager;
    // Handles all payment-related operations
    private final PaymentProcessor paymentProcessor;

    // Tracks the current ongoing transaction
    private Transaction currentTransaction;
    // Represents the current state of the vending machine
    private VendingMachineState currentState;

    public VendingMachine() {
        transactionHistory = new ArrayList<>();
        currentTransaction = new Transaction();
        inventoryManager = new InventoryManager();
        paymentProcessor = new PaymentProcessor();
        this.currentState = new NoMoneyInsertedState();
    }
    
    // Updates the state of the machine
    public void setState(VendingMachineState state) {
        this.currentState = state;
    }

    // STATE PATTERN OPERATIONS
    public void insertMoneyState(double amount) throws InvalidStateException {
        currentState.insertMoney(this, amount);
    }
    
    public void selectProductState(String rackId) throws InvalidStateException, InvalidTransactionException {
        currentState.selectProductByCode(this, rackId);
    }
    
    public void dispenseProductState() throws InvalidStateException, InvalidTransactionException {
        currentState.dispenseProduct(this);
    }
    
    public String getCurrentStateDescription() {
        return currentState.getStateDescription();
    }

    // INTERNAL STATE ACTIONS CALLED BY STATE CLASSES
    void addBalance(double amount) {
        paymentProcessor.addBalance(BigDecimal.valueOf(amount));
    }

    // Updates the rack configuration with new product racks
    void setRack(Map<String, Rack> rack) {
        inventoryManager.updateRack(rack);
    }

    // Selects a product from a specific rack
    void chooseProduct(String rackId) {
        final Product product = inventoryManager.getProductInRack(rackId);
        currentTransaction.setRack(inventoryManager.getRack(rackId));
        currentTransaction.setProduct(product);
    }

    // Processes and completes the current transaction
    Transaction confirmTransaction() throws InvalidTransactionException {
        // Step 1: Validate the transaction before processing
        validateTransaction();

        // Step 2: Charge the customer for the product
        paymentProcessor.charge(currentTransaction.getProduct().getUnitPrice());

        // Step 3: Dispense the product from the rack
        inventoryManager.dispenseProductFromRack(currentTransaction.getRack());

        // Step 4: Return the change to the customer
        currentTransaction.setTotalAmount(paymentProcessor.returnChange());

        // Step 5: Add the completed transaction to the history
        transactionHistory.add(currentTransaction);
        Transaction completedTransaction = currentTransaction;

        // Reset the current transaction for the next purchase.
        currentTransaction = new Transaction();
        return completedTransaction;
    }

    // Validates the current transaction for product availability and sufficient funds
    private void validateTransaction() throws InvalidTransactionException {
        if (currentTransaction.getProduct() == null) {
            throw new InvalidTransactionException("Invalid product selection. Product is null.");
        } else if (currentTransaction.getRack() == null || currentTransaction.getRack().getProductCount() == 0) {
            throw new InvalidTransactionException("Insufficient inventory for product.");
        } else if (paymentProcessor
                        .getCurrentBalance()
                        .compareTo(currentTransaction.getProduct().getUnitPrice())
                < 0) {
            throw new InvalidTransactionException("Insufficient funds. Inserted: " 
                + paymentProcessor.getCurrentBalance() + " Cost: " 
                + currentTransaction.getProduct().getUnitPrice());
        }
    }

    // Returns an unmodifiable list of all completed transactions
    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    // Cancels the current transaction and returns any inserted money
    public void cancelTransaction() {
        paymentProcessor.returnChange();
        currentTransaction = new Transaction(); // Reset the current transaction for the next purchase.
        this.currentState = new NoMoneyInsertedState();
    }

    // Returns the inventory manager instance
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
    
    // Returns the payment processor instance
    public PaymentProcessor getPaymentProcessor() {
        return paymentProcessor;
    }
}