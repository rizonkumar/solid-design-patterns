public class Motorcycle implements Vehicle {
    private String licensePlate;

    public Motorcycle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getLicensePlate() {
        return this.licensePlate;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }
}
