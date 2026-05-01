import java.math.BigDecimal;

public class VIPRate implements PricingStrategy {
    private final BigDecimal price;

    public VIPRate(BigDecimal price) {
        this.price = price;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
