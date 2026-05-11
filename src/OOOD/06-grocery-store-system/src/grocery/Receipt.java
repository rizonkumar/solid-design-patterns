package grocery;

import java.util.Date;
import java.util.UUID;

public class Receipt {
    private final String receiptId;
    private final Order order;
    private final Date issueDate;

    public Receipt(Order order) {
        this.receiptId = String.valueOf(UUID.randomUUID());
        this.order = order;
        this.issueDate = new Date();
    }

    public String printReceipt() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== RECEIPT =====\n");
        sb.append("Receipt ID: ").append(receiptId).append("\n");
        sb.append("Date: ").append(issueDate).append("\n");
        sb.append("Order ID: ").append(order.getOrderId()).append("\n");
        sb.append("-------------------\n");
        for (OrderItem item : order.getItems()) {
            sb.append(item.getItem().getName())
              .append(" x").append(item.getQuantity())
              .append(" : $").append(item.calculatePrice()).append("\n");
            
            DiscountCampaign discount = order.getAppliedDiscounts().get(item);
            if (discount != null) {
                sb.append("  (Discount Applied: ").append(discount.getName()).append(") : -$")
                  .append(item.calculatePrice().subtract(item.calculatePriceWithDiscount(discount))).append("\n");
            }
        }
        sb.append("-------------------\n");
        sb.append("Subtotal: $").append(order.calculateSubtotal()).append("\n");
        sb.append("Total: $").append(order.calculateTotal()).append("\n");
        sb.append("===================");
        return sb.toString();
    }
}
