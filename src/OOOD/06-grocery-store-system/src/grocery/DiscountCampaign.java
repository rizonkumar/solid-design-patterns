package grocery;

import java.math.BigDecimal;

public class DiscountCampaign {
    // Unique identifier for the discount campaign
    private final String discountId;
    // Name of the discount campaign
    private final String name;
    // Criteria that determines if the discount applies to an item or category
    private final DiscountCriteria criteria;
    // Strategy for calculating the discounted price
    private final DiscountCalculationStrategy calculationStrategy;

    // Creates a new discount campaign with the specified details
    public DiscountCampaign(
            String discountId,
            String name,
            DiscountCriteria criteria,
            DiscountCalculationStrategy calculationStrategy) {
        this.discountId = discountId;
        this.name = name;
        this.criteria = criteria;
        this.calculationStrategy = calculationStrategy;
    }

    // Checks if this discount applies to the given item
    public boolean isApplicable(Item item) {
        return criteria.isApplicable(item);
    }

    // Calculates the discounted price for the given order item
    public BigDecimal calculateDiscount(OrderItem item) {
        return calculationStrategy.calculateDiscountedPrice(item.calculatePrice());
    }
    
    public String getName() { return name; }
    public String getDiscountId() { return discountId; }
}
