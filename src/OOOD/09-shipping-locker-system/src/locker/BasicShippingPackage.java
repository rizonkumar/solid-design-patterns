package locker;

import java.math.BigDecimal;

public class BasicShippingPackage implements ShippingPackage {
    // Unique identifier for the order
    private final String orderId;
    // User account associated with this package
    private final Account user;
    private final BigDecimal width;
    private final BigDecimal height;
    private final BigDecimal depth;
    // Current status of the package
    private ShippingStatus status;

    // Creates a new shipping package with specified dimensions
    public BasicShippingPackage(
            String orderId, Account user, BigDecimal width, BigDecimal height, BigDecimal depth) {
        this.orderId = orderId;
        this.user = user;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.status = ShippingStatus.CREATED;
    }

    // Returns the current package status
    @Override
    public ShippingStatus getStatus() {
        return status;
    }

    // Updates the package status
    @Override
    public void updateShippingStatus(ShippingStatus status) {
        this.status = status;
    }

    // Determines the smallest locker size that can fit this package
    @Override
    public LockerSize getLockerSize() {
        for (LockerSize size : LockerSize.values()) {
            if (size.getWidth().compareTo(width) >= 0
                    && size.getHeight().compareTo(height) >= 0
                    && size.getDepth().compareTo(depth) >= 0) {
                return size;
            }
        }
        throw new PackageIncompatibleException("No locker size available for the package");
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public Account getUser() {
        return user;
    }

    @Override
    public BigDecimal getWidth() {
        return width;
    }

    @Override
    public BigDecimal getHeight() {
        return height;
    }

    @Override
    public BigDecimal getDepth() {
        return depth;
    }
}
