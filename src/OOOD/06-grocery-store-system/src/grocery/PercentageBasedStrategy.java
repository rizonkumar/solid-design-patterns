package grocery;

import java.math.BigDecimal;

public class PercentageBasedStrategy implements DiscountCalculationStrategy {
    private final BigDecimal discountPercentage;
    
    public PercentageBasedStrategy(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
    
    @Override
    public BigDecimal calculateDiscountedPrice(BigDecimal originalPrice) {
        BigDecimal discountFactor = BigDecimal.ONE.subtract(discountPercentage.divide(BigDecimal.valueOf(100)));
        return originalPrice.multiply(discountFactor);
    }
}
