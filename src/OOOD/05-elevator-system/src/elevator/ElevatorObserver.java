package elevator;

public interface ElevatorObserver {
    void update(int floor, Direction direction);
}
