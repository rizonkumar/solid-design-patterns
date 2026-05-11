package elevator;

import java.util.List;

public class ShortestSeekTimeFirstStrategy implements DispatchingStrategy {
    // Selects the elevator that is closest to the requested floor and moving in the same direction
    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, int floor, Direction direction) {
        ElevatorCar bestElevator = null;
        int shortestDistance = Integer.MAX_VALUE;

        for (ElevatorCar elevator : elevators) {
            // Calculate distance between elevator and requested floor
            int distance = Math.abs(elevator.getCurrentFloor() - floor);
            boolean canReach = elevator.getAccessibleFloors().isEmpty() || elevator.getAccessibleFloors().contains(floor);
            
            // Select elevator if it's idle or moving in the same direction, can reach the floor, and closer than the current best
            if (canReach && (elevator.isIdle() || elevator.getCurrentDirection() == direction)
                    && distance < shortestDistance) {
                bestElevator = elevator;
                shortestDistance = distance;
            }
        }
        
        // Fallback: If no elevator perfectly matches the direction condition, just find the closest capable one
        if (bestElevator == null) {
            for (ElevatorCar elevator : elevators) {
                int distance = Math.abs(elevator.getCurrentFloor() - floor);
                boolean canReach = elevator.getAccessibleFloors().isEmpty() || elevator.getAccessibleFloors().contains(floor);
                if (canReach && distance < shortestDistance) {
                    bestElevator = elevator;
                    shortestDistance = distance;
                }
            }
        }

        return bestElevator;
    }
}
