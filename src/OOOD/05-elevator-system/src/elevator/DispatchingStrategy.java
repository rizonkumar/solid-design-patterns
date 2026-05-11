package elevator;

import java.util.List;

public interface DispatchingStrategy {
    ElevatorCar selectElevator(List<ElevatorCar> elevators, int floor, Direction direction);
}
