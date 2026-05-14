package restaurant;

// Command that handles cancellations of order items
public class CancelCommand implements OrderCommand {
    private final OrderItem orderItem;

    public CancelCommand(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public void execute() {
        orderItem.cancel();
    }
}
