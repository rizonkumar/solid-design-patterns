package elevator;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.HashSet;

public class ElevatorCar {
    private final String id;
    private ElevatorStatus status;
    private final Queue<Integer> targetFloors;
    // Set of floors this elevator can service (Deep Dive topic)
    private final Set<Integer> accessibleFloors;

    public ElevatorCar(String id, int startingFloor, Set<Integer> accessibleFloors) {
        this.id = id;
        this.status = new ElevatorStatus(startingFloor, Direction.IDLE);
        this.targetFloors = new LinkedList<>();
        this.accessibleFloors = accessibleFloors != null ? accessibleFloors : new HashSet<>();
    }

    public String getId() { return id; }
    public ElevatorStatus getStatus() { return status; }
    public int getCurrentFloor() { return status.getCurrentFloor(); }
    public Direction getCurrentDirection() { return status.getCurrentDirection(); }
    public Set<Integer> getAccessibleFloors() { return accessibleFloors; }

    public void addFloorRequest(int floor) {
        // Only add the request if the floor is accessible by this elevator and not already in the queue
        if ((accessibleFloors.isEmpty() || accessibleFloors.contains(floor)) && !targetFloors.contains(floor)) {
            targetFloors.offer(floor);
            updateDirection(floor);
        }
    }

    public boolean isIdle() {
        return targetFloors.isEmpty();
    }

    private void updateDirection(int targetFloor) {
        if (status.getCurrentFloor() < targetFloor) {
            status = new ElevatorStatus(status.getCurrentFloor(), Direction.UP);
        } else if (status.getCurrentFloor() > targetFloor) {
            status = new ElevatorStatus(status.getCurrentFloor(), Direction.DOWN);
        }
    }
    
    // Custom logic to simulate real-world elevator step movement 
    public void move() {
        if (isIdle()) return;
        int nextFloor = targetFloors.peek();
        int currentFloor = status.getCurrentFloor();
        
        if (currentFloor < nextFloor) {
            currentFloor++;
            status = new ElevatorStatus(currentFloor, Direction.UP);
        } else if (currentFloor > nextFloor) {
            currentFloor--;
            status = new ElevatorStatus(currentFloor, Direction.DOWN);
        }
        
        if (currentFloor == nextFloor) {
            targetFloors.poll(); // Arrived, remove from queue
            if (isIdle()) {
                status = new ElevatorStatus(currentFloor, Direction.IDLE);
            } else {
                updateDirection(targetFloors.peek());
            }
        }
    }
}
