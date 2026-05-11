package elevator;

import java.util.List;

public class FirstComeFirstServeStrategy implements DispatchingStrategy {
    // Selects the first available elevator that is either idle or moving in the same direction
    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, int floor, Direction direction) {
        for (ElevatorCar elevator : elevators) {
            boolean canReach = elevator.getAccessibleFloors().isEmpty() || elevator.getAccessibleFloors().contains(floor);
            // Return first elevator that is idle or moving in the same direction
            if (canReach && (elevator.isIdle() || elevator.getCurrentDirection() == direction)) {
                return elevator;
            }
        }
        // If no suitable elevator is found, randomly select one that can reach the floor
        for (ElevatorCar elevator : elevators) {
            boolean canReach = elevator.getAccessibleFloors().isEmpty() || elevator.getAccessibleFloors().contains(floor);
            if (canReach) {
                return elevator;
            }
        }
        return null;
    }
}
