package grocery;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Grocery Store System Initialization ===");
        
        GroceryStoreSystem system = new GroceryStoreSystem();
        
        // 1. Setup Catalog
        Item apple = new Item("Apple", "1001", "Produce", new BigDecimal("1.50"));
        Item milk = new Item("Milk", "1002", "Dairy", new BigDecimal("3.00"));
        Item tv = new Item("TV", "2001", "Electronics", new BigDecimal("250.00"));
        
        system.addOrUpdateItem(apple);
        system.addOrUpdateItem(milk);
        system.addOrUpdateItem(tv);
        
        // 2. Add Stock
        system.updateInventory("1001", 50);
        system.updateInventory("1002", 20);
        system.updateInventory("2001", 5);
        
        // 3. Setup Basic Discounts
        // 50 cents off Dairy
        DiscountCampaign dairyDiscount = new DiscountCampaign(
            "D1", "50c off Dairy", 
            new CategoryBasedCriteria("Dairy"), 
            new AmountBasedStrategy(new BigDecimal("0.50"))
        );
        system.addDiscountCampaign(dairyDiscount);
        
        // 4. Setup Composite & Decorator Discounts (Deep Dive)
        // Applies to Produce AND a specific Barcode 1001
        CompositeCriteria compositeCriteria = new CompositeCriteria(Arrays.asList(
            new CategoryBasedCriteria("Produce"),
            new ItemBasedCriteria("1001")
        ));
        
        // Strategy: 10% off + fixed 25 cents off
        DiscountCalculationStrategy baseStrategy = new PercentageBasedStrategy(new BigDecimal("10.0")); // 10% off
        DiscountCalculationStrategy decoratedStrategy = new FixedDiscountDecorator(baseStrategy, new BigDecimal("0.25")); // + fixed 25c off
        
        DiscountCampaign specialAppleDiscount = new DiscountCampaign(
            "D2", "Special Apple Promo", 
            compositeCriteria, 
            decoratedStrategy
        );
        system.addDiscountCampaign(specialAppleDiscount);
        
        // 5. Run Checkout
        System.out.println("Starting Checkout process...");
        Checkout checkout = system.getCheckout();
        checkout.startNewOrder();
        
        checkout.addItemToOrder(apple, 4); // 4 Apples: 1.50 * 4 = 6.00. 10% off -> 5.40, then -0.25 -> 5.15
        checkout.addItemToOrder(milk, 2); // 2 Milks: 3.00 * 2 = 6.00. -0.50 off per unit -> wait, AmountBased applies to whole total in OrderItem? My logic subtracts from originalPrice. So 6.00 - 0.50 = 5.50
        checkout.addItemToOrder(tv, 1); // 250.00, no discount
        
        System.out.println("Total after discounts: $" + checkout.getOrderTotal());
        
        BigDecimal change = checkout.processPayment(new BigDecimal("300.00"));
        System.out.println("Customer paid $300.00. Change: $" + change);
        
        System.out.println("\n" + checkout.getReceipt().printReceipt());
    }
}
