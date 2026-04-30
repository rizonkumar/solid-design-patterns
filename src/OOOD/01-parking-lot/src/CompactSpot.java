public class CompactSpot implements ParkingSpot {
    private int spotNumber;
    private Vehicle vehicle; // the vehicle currenlty occuping this spot

    public CompactSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.vehicle = null; // No vehicle occupying initially
    }

    @Override
    public int getSpotNumber() {
        return spotNumber;
    }

    @Override
    public boolean isAvailable() {
        return vehicle == null;
    }

    @Override
    public void occupy(Vehicle vehicle) {
        if(isAvailable()) {
            this.vehicle = vehicle;
        } else {
            // Spot is already occupied
        }
    }

    @Override
    public void vacate() {
        this.vehicle = null;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }

    /**
     * For brevity, we omit the full code of RegularSpot and OversizedSpot, but they follow a similar structure:
     *
     * RegularSpot: Returns VehicleSize.MEDIUM, suitable for medium-sized vehicles like cars.
     * OversizedSpot: Returns VehicleSize.LARGE, designed for large vehicles like trucks.
     */
}
