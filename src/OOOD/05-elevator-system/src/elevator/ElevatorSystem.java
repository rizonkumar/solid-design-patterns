package elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {

    private final List<ElevatorCar> elevators;
    private final ElevatorDispatch dispatchController;

    public ElevatorSystem(List<ElevatorCar> elevators, DispatchingStrategy strategy) {
        this.elevators = elevators;
        this.dispatchController = new ElevatorDispatch(strategy);
    }

    // Returns the current status of all elevators in the system
    public List<ElevatorStatus> getAllElevatorStatuses() {
        List<ElevatorStatus> statuses = new ArrayList<>();
        for (ElevatorCar elevator : elevators) {
            statuses.add(elevator.getStatus());
        }
        return statuses;
    }

    // Handles a request for an elevator from a specific floor and direction
    public void requestElevator(int currentFloor, Direction direction) {
        dispatchController.dispatchElevatorCar(currentFloor, direction, elevators);
    }

    // Handles a floor selection request from inside an elevator
    public void selectFloor(ElevatorCar car, int destinationFloor) {
        car.addFloorRequest(destinationFloor);
    }
    
    // Simulate time passing to move elevators
    public void step() {
        for (ElevatorCar elevator : elevators) {
            elevator.move();
        }
    }
    
    public List<ElevatorCar> getElevators() {
        return elevators;
    }
}
