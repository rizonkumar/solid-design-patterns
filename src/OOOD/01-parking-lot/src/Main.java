import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Initialize strategies
        FareStrategy baseFareStrategy = new BaseFareStrategy();
        FareStrategy peakHoursFareStrategy = new PeakHoursFareStrategy();
        
        // Initialize Calculator
        FareCalculator fareCalculator = new FareCalculator(Arrays.asList(baseFareStrategy, peakHoursFareStrategy));

        // Initialize Manager
        ParkingManager parkingManager = new ParkingManager();
        
        // Add some spots to the manager
        parkingManager.addParkingSpace(new CompactSpot(1));
        parkingManager.addParkingSpace(new RegularSpot(2));
        parkingManager.addParkingSpace(new OversizedSpot(3));

        // Initialize Parking Lot
        ParkingLot parkingLot = new ParkingLot(parkingManager, fareCalculator);

        // Create Vehicles
        Vehicle motorcycle = new Motorcycle("MOTO-123");
        Vehicle car = new Car("CAR-456");
        Vehicle truck = new Truck("TRK-789");

        System.out.println("--- Welcome to the Parking Lot ---\n");

        // Park Vehicles
        Ticket ticket1 = parkingLot.enterVehicle(motorcycle);
        if (ticket1 != null) {
            System.out.println("Motorcycle parked successfully. Ticket ID: " + ticket1.getId());
        }

        Ticket ticket2 = parkingLot.enterVehicle(car);
        if (ticket2 != null) {
            System.out.println("Car parked successfully. Ticket ID: " + ticket2.getId());
        }

        Ticket ticket3 = parkingLot.enterVehicle(truck);
        if (ticket3 != null) {
            System.out.println("Truck parked successfully. Ticket ID: " + ticket3.getId());
        }

        System.out.println("\n--- Leaving the Parking Lot ---");

        // Unpark Vehicles and calculate fare
        // Note: Fare might be 0 since they are leaving immediately, 
        // but this demonstrates the flow.
        BigDecimal fare1 = parkingLot.leaveVehicle(ticket1);
        System.out.println("Motorcycle left. Fare: $" + fare1);

        BigDecimal fare2 = parkingLot.leaveVehicle(ticket2);
        System.out.println("Car left. Fare: $" + fare2);
    }
}
