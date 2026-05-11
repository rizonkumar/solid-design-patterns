package elevator;

public class ElevatorStatus {
    private final int currentFloor;
    private final Direction currentDirection;

    public ElevatorStatus(int currentFloor, Direction currentDirection) {
        this.currentFloor = currentFloor;
        this.currentDirection = currentDirection;
    }

    public int getCurrentFloor() { return currentFloor; }
    public Direction getCurrentDirection() { return currentDirection; }
}
