package vending;

class VendingMachine {
    // Stores the history of all completed transactions
    private final List<Transaction> transactionHistory;
    // Manages the inventory of products in the vending machine
    private final InventoryManager inventoryManager;
    // Handles all payment-related operations
    private final PaymentProcessor paymentProcessor;
    // Represents the current state of the vending machine
    private final VendingMachineState currentState;
    // Tracks the current balance in the machine
    private final double balance;
    // Stores the currently selected product code
    private final String selectedProduct;
    // Tracks the current ongoing transaction
    private Transaction currentTransaction;

    public VendingMachine() {
        transactionHistory = new ArrayList<>();
        currentTransaction = new Transaction();
        inventoryManager = new InventoryManager();
        paymentProcessor = new PaymentProcessor();
        this.currentState = new NoMoneyInsertedState();
        this.balance = 0.0;
        this.selectedProduct = null;
    }

    // Updates the rack configuration with new product racks
    void setRack(Map<String, Rack> rack) {
        inventoryManager.updateRack(rack);
    }

    // Adds money to the payment processor
    void insertMoney(final BigDecimal amount) {
        paymentProcessor.addBalance(amount);
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
            throw new InvalidTransactionException("Invalid product selection");
        } else if (currentTransaction.getRack().getProductCount() == 0) {
            throw new InvalidTransactionException("Insufficient inventory for product.");
        } else if (paymentProcessor
                .getCurrentBalance()
                .compareTo(currentTransaction.getProduct().getUnitPrice())
                < 0) {
            throw new InvalidTransactionException("Insufficient fund");
        }
    }

    // Returns an unmodifiable list of all completed transactions
    public List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    // Cancels the current transaction and returns any inserted money
    public void cancelTransaction() {
        paymentProcessor.returnChange();
        currentTransaction =
                new Transaction(); // Reset the current transaction for the next purchase.
    }

    // Returns the inventory manager instance
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }
}