package grocery;

import java.math.BigDecimal;

public class OrderItem {
    // The item being ordered
    private final Item item;
    // Quantity of the item
    private final int quantity;

    // Creates a new order item with the specified item and quantity
    public OrderItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    // Calculates the total price for this order item without any discount
    public BigDecimal calculatePrice() {
        return item.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    // Calculates the total price for this order item with the given discount
    public BigDecimal calculatePriceWithDiscount(DiscountCampaign newDiscount) {
        return newDiscount.calculateDiscount(this);
    } 
    
    public Item getItem() { return item; }
    public int getQuantity() { return quantity; }
}
