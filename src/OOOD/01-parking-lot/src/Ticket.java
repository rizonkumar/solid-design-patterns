public class Ticket {
    private final String ticketId; // Unique ticket identifier
    private final Vehicle vehicle; // The vehicle associated with the ticket
    // The parking spot where the vehicle is parked     private final ParkingSpot parkingSpot;
    // // The time the vehicle entered the parking lot
    private final LocalDateTime entryTime; // The time the vehicle exited the parking lot
    private LocalDateTime exitTime;

    public Ticket(
            String ticketId, Vehicle vehicle, ParkingSpot parkingSpot, LocalDateTime entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
        // Initially, exitTime is null because the vehicle is still parked         this.exitTime =
        // null;
    }

    public BigDecimal calculateParkingDuration() {
        return new BigDecimal(
                Duration.between(
                                entryTime,
                                Objects.requireNonNullElseGet(exitTime, LocalDateTime::now))
                        .toMinutes());
    } // getter and setter methods are omitted for brevity
}