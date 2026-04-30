import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingLot {
    private final ParkingManager parkingManager;
    private final FareCalculator fareCalculator;

    public ParkingLot(ParkingManager parkingManager, FareCalculator fareCalculator) {
        this.parkingManager = parkingManager;
        this.fareCalculator = fareCalculator;
    }

    // Method to handle vehicle entry into the parking lot
    public Ticket enterVehicle(Vehicle vehicle) {
        // Delegate parking logic to ParkingManager
        ParkingSpot spot = parkingManager.parkVehicle(vehicle);

        if (spot != null) {
            // Create ticket with entry time
            Ticket ticket = new Ticket(generateTicketId(), vehicle, spot, LocalDateTime.now());
            return ticket;
        } else {
            return null; // No spot available
        }
    }

    private String generateTicketId() {
        return UUID.randomUUID().toString();
    }

    // Method to handle vehicle exit from the parking lot
    public BigDecimal leaveVehicle(Ticket ticket) {
        // Ensure the ticket is valid and the vehicle hasn't already left
        if (ticket != null && ticket.getExitTime() == null) {
            // Set exit time
            ticket.setExitTime(LocalDateTime.now());

            // Delegate unparking logic to ParkingManager
            parkingManager.unparkVehicle(ticket.getVehicle());

            // Calculate the fare
            BigDecimal fare = fareCalculator.calculateFare(ticket);
            return fare;
        } else {
            return BigDecimal.ZERO;
            // Invalid ticket or vehicle already exited.
        }
    }
}