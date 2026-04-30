import java.math.BigDecimal;

public class BaseFareStrategy implements FareStrategy {
    private static final BigDecimal SMALL_VEHICLE_RATE = new BigDecimal("1.0");
    private static final BigDecimal MEDIUM_VEHICLE_RATE = new BigDecimal("2.0");
    private static final BigDecimal LARGE_VEHICLE_RATE = new BigDecimal("3.0");

    // Calculate fare based on the duration and add it to the input fare to return a new total
    @Override
    public BigDecimal calculateFare(Ticket ticket, BigDecimal inputFare) {
        BigDecimal fare = inputFare;
        BigDecimal rate;
        switch (ticket.getVehicle().getSize()) {
            case MEDIUM:
                rate = MEDIUM_VEHICLE_RATE;
                break;
            case LARGE:
                rate = LARGE_VEHICLE_RATE;
                break;
            default:
                rate = SMALL_VEHICLE_RATE;
        }
        fare = fare.add(rate.multiply(ticket.calculateParkingDuration()));
        return fare;
    }
}
