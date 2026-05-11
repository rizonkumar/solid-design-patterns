package grocery;

import java.math.BigDecimal;

public class AmountBasedStrategy implements DiscountCalculationStrategy {
    private final BigDecimal discountAmount;
    
    public AmountBasedStrategy(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    @Override
    public BigDecimal calculateDiscountedPrice(BigDecimal originalPrice) {
        BigDecimal result = originalPrice.subtract(discountAmount);
        return result.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : result;
    }
}
