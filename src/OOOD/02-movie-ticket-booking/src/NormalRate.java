import java.math.BigDecimal;

public class NormalRate implements PricingStrategy {
    private final BigDecimal price;

    public NormalRate(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
