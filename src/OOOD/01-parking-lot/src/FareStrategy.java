public interface FareStrategy {
    BigDecimal calculateFare(Ticket ticket, BigDecimal inputFare);
}