package restaurant;

// Represents a food item ordered by a customer with its current status in the order process
public class OrderItem {
    private final MenuItem item;
    private Status status = Status.PENDING;

    public OrderItem(MenuItem item) {
        this.item = item;
    }

    public MenuItem getItem() {
        return item;
    }

    public Status getStatus() {
        return status;
    }

    // Updates the status to indicate the item has been sent to the kitchen
    public void sendToKitchen() {
        if (status == Status.PENDING) status = Status.SENT_TO_KITCHEN;
    }

    // Updates the status to indicate the item has been delivered to the customer
    public void deliverToCustomer() {
        if (status == Status.SENT_TO_KITCHEN) status = Status.DELIVERED;
    }

    // Updates the status to indicate the item has been canceled
    public void cancel() {
        if (status == Status.PENDING || status == Status.SENT_TO_KITCHEN) {
            status = Status.CANCELED;
        }
    }
}
