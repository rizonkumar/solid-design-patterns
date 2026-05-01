package booking;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal getPrice();
}
