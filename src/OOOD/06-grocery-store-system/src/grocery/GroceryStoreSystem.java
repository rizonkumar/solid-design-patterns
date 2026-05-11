package grocery;

import java.util.ArrayList;
import java.util.List;

public class GroceryStoreSystem {
    // Product catalog containing all available items
    private final Catalog catalog;
    // Inventory tracking system
    private final Inventory inventory;
    // List of active discount campaigns
    private final List<DiscountCampaign> activeDiscounts = new ArrayList<>();
    // Checkout system for processing orders
    private final Checkout checkout;

    public GroceryStoreSystem() {
        this.catalog = new Catalog();
        this.inventory = new Inventory();
        this.checkout = new Checkout(activeDiscounts);
    }

    // Adds or updates an item in the catalog
    public void addOrUpdateItem(Item item) {
        catalog.updateItem(item);
    }

    // Updates the inventory count for an item
    public void updateInventory(String barcode, int count) {
        inventory.addStock(barcode, count);
    }

    // Adds a new discount campaign to the system
    public void addDiscountCampaign(DiscountCampaign discount) {
        activeDiscounts.add(discount);
    }

    // Retrieves an item from the catalog by its barcode
    public Item getItemByBarcode(String barcode) {
        return catalog.getItem(barcode);
    }

    // Removes an item from the catalog
    public void removeItem(String barcode) {
        catalog.removeItem(barcode);
    }
    
    public Checkout getCheckout() {
        return checkout;
    }
    
    public Inventory getInventory() {
        return inventory;
    }
}
