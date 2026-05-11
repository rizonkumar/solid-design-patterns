package elevator;

import java.util.ArrayList;
import java.util.List;

// Observable Subject: HallwayButtonPanel
public class HallwayButtonPanel {
    private final int floor;
    private final List<ElevatorObserver> observers;

    public HallwayButtonPanel(int floor) {
        this.floor = floor;
        this.observers = new ArrayList<>();
    }

    // Handles button press event and notifies all registered observers
    public void pressButton(Direction direction) {
        notifyObservers(direction);
    }

    // Registers a new observer to receive button press notifications
    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    // Notifies all registered observers about the button press
    private void notifyObservers(Direction direction) {
        for (ElevatorObserver observer : observers) {
            observer.update(floor, direction);
        }
    }
}
