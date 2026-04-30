public class Car implements Vehicle {
    public String licensePlate;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getLicensePlate() {
        return this.licensePlate;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.MEDIUM;
    }

   // For the sake of brevity, we have not shown the code for the Motorcycle and Truck classes.
}