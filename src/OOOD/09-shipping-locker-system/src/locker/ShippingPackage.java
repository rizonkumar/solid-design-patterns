package locker;

import java.math.BigDecimal;

public interface ShippingPackage {
    String getOrderId();
    Account getUser();
    BigDecimal getWidth();
    BigDecimal getHeight();
    BigDecimal getDepth();
    ShippingStatus getStatus();
    void updateShippingStatus(ShippingStatus status);
    LockerSize getLockerSize();
}
