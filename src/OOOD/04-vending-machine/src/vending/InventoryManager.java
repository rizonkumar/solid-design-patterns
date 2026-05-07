package vending;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    // Maps rack codes to their corresponding rack objects
    private Map<String, Rack> racks;

    public InventoryManager() {
        racks = new HashMap<>();
    }

    // Retrieves the product from a specific rack using its code
    public Product getProductInRack(String rackCode) {
        Rack rack = racks.get(rackCode);
        return rack != null ? rack.getProduct() : null;
    }

    // Dispenses a product from the specified rack and decrements its count
    public void dispenseProductFromRack(Rack rack) {
        if (rack.getProductCount() > 0) {
            rack.setCount(rack.getProductCount() - 1);
        } else {
            throw new IllegalStateException("Cannot dispense product. Rack is empty.");
        }
    }

    public void updateRack(Map<String, Rack> racks) {
        this.racks = racks;
    }

    public Rack getRack(String name) {
        return racks.get(name);
    }
}