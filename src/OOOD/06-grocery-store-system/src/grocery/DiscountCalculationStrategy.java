package grocery;

import java.math.BigDecimal;

public interface DiscountCalculationStrategy {
    BigDecimal calculateDiscountedPrice(BigDecimal originalPrice);
}
