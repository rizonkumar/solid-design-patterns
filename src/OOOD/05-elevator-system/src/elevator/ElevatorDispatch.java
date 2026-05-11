package elevator;

import java.util.List;

public class ElevatorDispatch {
    private final DispatchingStrategy strategy;

    public ElevatorDispatch(DispatchingStrategy strategy) {
        this.strategy = strategy;
    }

    // Handles requests from the hallway button and assigns an elevator based on the dispatching strategy.
    public void dispatchElevatorCar(int floor, Direction direction, List<ElevatorCar> elevators) {
        ElevatorCar selectedElevator = strategy.selectElevator(elevators, floor, direction);
        if (selectedElevator != null) {
            selectedElevator.addFloorRequest(floor);
        } else {
            System.err.println("No suitable elevator found capable of reaching floor " + floor);
        }
    }
}
