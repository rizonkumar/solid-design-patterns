package booking;

public class Seat {
    private final String seatNumber;
    private PricingStrategy pricingStrategy;

    public Seat(String seatNumber, PricingStrategy pricingStrategy) {
        this.seatNumber = seatNumber;
        this.pricingStrategy = pricingStrategy;
    }

    public String getSeatNumber() { return seatNumber; }
    public PricingStrategy getPricingStrategy() { return pricingStrategy; }
    public void setPricingStrategy(PricingStrategy pricingStrategy) { this.pricingStrategy = pricingStrategy; }
}
