package grocery;

import java.math.BigDecimal;
import java.util.List;

public class Checkout {
    // Current order being processed
    private Order currentOrder;
    // List of active discount campaigns
    private final List<DiscountCampaign> activeDiscounts;

    // Creates a new checkout with the given active discounts
    public Checkout(List<DiscountCampaign> activeDiscounts) {
        this.activeDiscounts = activeDiscounts;
        startNewOrder();
    }

    // Starts a new order
    public void startNewOrder() {
        this.currentOrder = new Order();
    }

    // Processes the payment and returns the change
    public BigDecimal processPayment(BigDecimal paymentAmount) {
        currentOrder.setPayment(paymentAmount);
        return currentOrder.calculateChange();
    }

    // Adds an item to the current order and applies applicable discounts
    public void addItemToOrder(Item item, int quantity) {
        OrderItem orderItem = new OrderItem(item, quantity);
        currentOrder.addItem(orderItem);

        for (DiscountCampaign newDiscount : activeDiscounts) {
            if (newDiscount.isApplicable(item)) {
                // if there are multiple newDiscount that apply to item, apply the higher one
                if (currentOrder.getAppliedDiscounts().containsKey(orderItem)) {
                    DiscountCampaign existingDiscount =
                            currentOrder.getAppliedDiscounts().get(orderItem);
                    if (orderItem
                                    .calculatePriceWithDiscount(newDiscount)
                                    .compareTo(
                                            orderItem.calculatePriceWithDiscount(existingDiscount))
                            < 0) { // Should be less to mean better discount for user
                        currentOrder.applyDiscount(orderItem, newDiscount);
                    }
                } else {
                    currentOrder.applyDiscount(orderItem, newDiscount);
                }
            }
        }
    }

    // Generates a receipt for the current order
    public Receipt getReceipt() {
        return new Receipt(currentOrder);
    }

    // Calculates the total amount for the current order
    public BigDecimal getOrderTotal() {
        return currentOrder.calculateTotal();
    }
}
