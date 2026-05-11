package elevator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Elevator System Initialization ===");
        
        // Elevator 1 handles all floors (1 to 20).
        ElevatorCar car1 = new ElevatorCar("Elevator-1", 1, new HashSet<>()); 
        
        // Elevator 2 handles specific floors (1, 5, 10, 15, 20).
        ElevatorCar car2 = new ElevatorCar("Elevator-2", 5, new HashSet<>(Arrays.asList(1, 5, 10, 15, 20)));
        
        List<ElevatorCar> allElevators = Arrays.asList(car1, car2);

        // Instantiate system with SSTF strategy
        ElevatorSystem system = new ElevatorSystem(allElevators, new ShortestSeekTimeFirstStrategy());
        
        System.out.println("Initial State:");
        printElevatorStatus(allElevators);

        System.out.println("\n=== Testing standard Request (Hallway Button) ===");
        System.out.println("User on floor 6 presses DOWN button.");
        system.requestElevator(6, Direction.DOWN);
        
        System.out.println("\nSimulating elevator movement...");
        for (int i = 0; i < 6; i++) {
            system.step();
            printElevatorStatus(allElevators);
        }
        
        System.out.println("\n=== Testing accessible floors constraint ===");
        System.out.println("User on floor 3 presses UP button. Car 2 cannot reach floor 3. Car 1 should be dispatched.");
        system.requestElevator(3, Direction.UP);
        
        System.out.println("\nSimulating elevator movement...");
        for (int i = 0; i < 4; i++) {
            system.step();
            printElevatorStatus(allElevators);
        }
        
        System.out.println("\n=== Testing Observer Pattern (Deep Dive) ===");
        HallwayButtonPanel floor10Panel = new HallwayButtonPanel(10);
        ElevatorDispatch dispatcher = new ElevatorDispatch(new ShortestSeekTimeFirstStrategy());
        ElevatorDispatchController observer = new ElevatorDispatchController(dispatcher, allElevators);
        
        floor10Panel.addObserver(observer);
        System.out.println("User on floor 10 presses UP. Observer triggers dispatch...");
        floor10Panel.pressButton(Direction.UP);
        
        System.out.println("\nSimulating elevator movement...");
        for (int i = 0; i < 8; i++) {
            system.step();
            printElevatorStatus(allElevators);
        }
    }
    
    private static void printElevatorStatus(List<ElevatorCar> elevators) {
        for (ElevatorCar car : elevators) {
            System.out.print("[" + car.getId() + " Floor:" + car.getCurrentFloor() + " Dir:" + car.getCurrentDirection() + "] ");
        }
        System.out.println();
    }
}
