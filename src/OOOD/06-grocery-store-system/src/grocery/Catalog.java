package grocery;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    // Map of barcodes to their corresponding items
    private final Map<String, Item> items = new HashMap<>();

    public void updateItem(Item item) {
        items.put(item.getBarcode(), item);
    }

    public void removeItem(String barcode) {
        items.remove(barcode);
    }

    public Item getItem(String barcode) {
        return items.get(barcode);
    }
}
