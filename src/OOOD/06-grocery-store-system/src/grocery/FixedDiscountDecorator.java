package grocery;

import java.math.BigDecimal;

public class FixedDiscountDecorator implements DiscountCalculationStrategy {
    // The strategy being decorated
    private final DiscountCalculationStrategy strategy;
    // The fixed amount to be added to the discount
    private final BigDecimal fixedAmount;

    public FixedDiscountDecorator(DiscountCalculationStrategy strategy, BigDecimal fixedAmount) {
        this.strategy = strategy;
        this.fixedAmount = fixedAmount;
    }

    // Calculates the discounted price by applying both the base strategy and the fixed amount
    @Override
    public BigDecimal calculateDiscountedPrice(BigDecimal originalPrice) {
        BigDecimal base = strategy.calculateDiscountedPrice(originalPrice);
        BigDecimal result = base.subtract(fixedAmount);
        return result.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : result;
    }
}
