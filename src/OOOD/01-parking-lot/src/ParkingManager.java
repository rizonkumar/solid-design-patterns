public class ParkingManager {
    private final Map<VehicleSize, List<ParkingSpot>> availableSpots;
    private final Map<Vehicle, ParkingSpot> vehicleToSpotMap;
    private final Map<ParkingSpot, Vehicle> spotToVehicleMap;

    // Create Parking Manager based on a given map of available spots
    public ParkingManager(Map<VehicleSize, List<ParkingSpot>> availableSpots) {
        this.availableSpots = availableSpots;
        this.vehicleToSpotMap = new HashMap<>();
        this.spotToVehicleMap = new HashMap<>();
    }

    public ParkingSpot findSpotForVehicle(Vehicle vehicle) {
        // No change in the method
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findSpotForVehicle(vehicle);
        if (spot != null) {
            spot.occupy(vehicle);
            // Record bidirectional mapping
            vehicleToSpotMap.put(vehicle, spot);
            spotToVehicleMap.put(spot, vehicle);
            // Remove the spot from the available list
            availableSpots.get(spot.getSize()).remove(spot);
            return spot; // Parking successful
        }
        return null; // No spot found for this vehicle
    }

    public void unparkVehicle(Vehicle vehicle) {
        ParkingSpot spot = vehicleToSpotMap.remove(vehicle);
        if (spot != null) {
            spotToVehicleMap.remove(spot);
            spot.vacate();
            availableSpots.get(spot.getSize()).add(spot);
        }
    }

    // Find vehicle's parking spot
    public ParkingSpot findVehicleBySpot(Vehicle vehicle) {
        return vehicleToSpotMap.get(vehicle);
    }

    // Find which vehicle is parked in a spot
    public Vehicle findSpotByVehicle(ParkingSpot spot) {
        return spotToVehicleMap.get(spot);
    }
}
