import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PeakHoursFareStrategy implements FareStrategy {
    // 50% higher during peak hours
    private static final BigDecimal PEAK_HOURS_MULTIPLIER = new BigDecimal("1.5");

    public PeakHoursFareStrategy() {}

    @Override
    public BigDecimal calculateFare(Ticket ticket, BigDecimal inputFare) {
        BigDecimal fare = inputFare;
        if (isPeakHours(ticket.getEntryTime())) {
            fare = fare.multiply(PEAK_HOURS_MULTIPLIER);
        }
        return fare;
    }

    private boolean isPeakHours(LocalDateTime time) {
        int hour = time.getHour();
        return (hour >= 7 && hour <= 10) || (hour >= 16 && hour <= 19);
    }
}