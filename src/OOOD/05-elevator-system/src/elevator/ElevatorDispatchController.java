package elevator;

import java.util.List;

// Observer Implementation: ElevatorDispatchController
public class ElevatorDispatchController implements ElevatorObserver {
    private final ElevatorDispatch dispatcher;
    private final List<ElevatorCar> elevators;
    
    public ElevatorDispatchController(ElevatorDispatch dispatcher, List<ElevatorCar> elevators) {
        this.dispatcher = dispatcher;
        this.elevators = elevators;
    }

    @Override
    public void update(int floor, Direction direction) {
        // Logic to handle the floor request using the underlying dispatcher
        dispatcher.dispatchElevatorCar(floor, direction, elevators);
    }
}
