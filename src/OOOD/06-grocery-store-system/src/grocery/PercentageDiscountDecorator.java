package grocery;

import java.math.BigDecimal;

public class PercentageDiscountDecorator implements DiscountCalculationStrategy {
    // The strategy being decorated
    private final DiscountCalculationStrategy strategy;
    // The additional percentage to be discounted
    private final BigDecimal additionalPercentage;

    public PercentageDiscountDecorator(
            DiscountCalculationStrategy strategy, BigDecimal additionalPercentage) {
        this.strategy = strategy;
        this.additionalPercentage = additionalPercentage;
    }

    // Calculates the discounted price by applying both the base strategy and the additional
    // percentage
    @Override
    public BigDecimal calculateDiscountedPrice(BigDecimal originalPrice) {
        BigDecimal baseDiscountedPrice = strategy.calculateDiscountedPrice(originalPrice);
        return baseDiscountedPrice.multiply(
                BigDecimal.ONE.subtract(additionalPercentage.divide(BigDecimal.valueOf(100))));
    }
}
