package vending;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Vending Machine System ---");

        // 1. Initialize Machine
        VendingMachine vm = new VendingMachine();

        // 2. Setup Products & Racks (Admin step)
        Product coke = new Product("COKE", "Coca-Cola", new BigDecimal("1.50"));
        Product chips = new Product("CHIPS", "Lays Classic", new BigDecimal("1.25"));
        Product water = new Product("WATER", "Dasani", new BigDecimal("1.00"));

        Map<String, Rack> initialRacks = new HashMap<>();
        initialRacks.put("A1", new Rack("A1", coke, 5));
        initialRacks.put("B1", new Rack("B1", chips, 3));
        initialRacks.put("C1", new Rack("C1", water, 10));
        vm.setRack(initialRacks);

        System.out.println("Current State: " + vm.getCurrentStateDescription());

        // 3. User Interaction - Buy Coke
        try {
            System.out.println("\n[User inserts $2.00]");
            vm.insertMoneyState(2.00);
            System.out.println("Current State: " + vm.getCurrentStateDescription());

            System.out.println("[User selects A1]");
            vm.selectProductState("A1");
            System.out.println("Current State: " + vm.getCurrentStateDescription());

            System.out.println("[Machine dispensing...]");
            vm.dispenseProductState();
            System.out.println("Success! Transaction finished.");
            System.out.println("Current State: " + vm.getCurrentStateDescription());
            
            // Check History
            Transaction lastTx = vm.getTransactionHistory().get(0);
            System.out.println("Transaction History: Bought " + lastTx.getProduct().getDescription() 
                    + " | Change Returned: $" + lastTx.getTotalAmount());
            System.out.println("Remaining inventory for A1: " + vm.getInventoryManager().getRack("A1").getProductCount());

        } catch (Exception e) {
            System.err.println("Error during purchase: " + e.getMessage());
        }
        
        // 4. Test failure (Not enough money)
        try {
            System.out.println("\n[User inserts $1.00 and tries to buy Chips ($1.25)]");
            vm.insertMoneyState(1.00);
            vm.selectProductState("B1");
            vm.dispenseProductState(); // Will fail validation
        } catch (Exception e) {
            System.err.println("Expected Error during purchase: " + e.getMessage());
            vm.cancelTransaction();
            System.out.println("Transaction cancelled. State reset: " + vm.getCurrentStateDescription());
        }
    }
}
