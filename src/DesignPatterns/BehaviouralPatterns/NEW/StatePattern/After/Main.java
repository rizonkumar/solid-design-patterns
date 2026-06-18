package DesignPatterns.BehaviouralPatterns.NEW.StatePattern.After;

import java.util.*;

// ============================================================================
// 1. THE CONTEXT CLASS
// ============================================================================
/**
 * The Context class acts as the interface to the outside world. It maintains a 
 * polymorphic reference to the active state object. Notice that it contains no 
 * conditional branches or state transition rules itself; it delegates all requests
 * to its internal state implementation object.
 */
class OrderContext {
    // COMPOSITION: Reference to the State Interface abstraction
    private OrderState currentState;

    /**
     * Constructor sets the initial, entry-level configuration.
     */
    public OrderContext() {
        this.currentState = new OrderPlacedState(); // Default baseline state
    }

    /**
     * State Mutator: Allows state objects to change the state of the context 
     * dynamically at runtime. This drives the actual transition wheel.
     */
    public void setState(OrderState state) {
        this.currentState = state;
    }

    /**
     * DELEGATION: Instead of running switch statements, the context simply says:
     * "I don't know my state logic, handle this transaction for me."
     */
    public void next() {
        currentState.next(this); // Passing 'this' lets the state object invoke context.setState()
    }

    public void cancel() {
        currentState.cancel(this);
    }

    public String getCurrentState() {
        return currentState.getStateName();
    }
}

// ============================================================================
// 2. STATE INTERFACE
// ============================================================================
/**
 * Defines the contract representing every legal business operation or state transition 
 * trigger that can occur within the lifecycle of the order process.
 */
interface OrderState {
    void next(OrderContext context);   // Request for sequential progression
    void cancel(OrderContext context); // Request for transactional cancellation
    String getStateName();             // Type identifier replacing loose magic strings
}

// ============================================================================
// 3. CONCRETE STATES (Encapsulated Behaviors)
// ============================================================================
/**
 * Each concrete state class isolates the unique business rules for that specific stage.
 * It encapsulates what is valid, what is blocked, and which state follows next.
 */
class OrderPlacedState implements OrderState {
    @Override
    public void next(OrderContext context) {
        // Enforcing a precise structural move from PLACED to PREPARING
        context.setState(new PreparingState());
        System.out.println("Order is now being prepared.");
    }

    @Override
    public void cancel(OrderContext context) {
        // Cancellation is completely valid during initial placement phases
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    @Override
    public String getStateName() {
        return "ORDER_PLACED";
    }
}

class PreparingState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setState(new OutForDeliveryState());
        System.out.println("Order is out for delivery.");
    }

    @Override
    public void cancel(OrderContext context) {
        // Cancellation is still permissible while items are being packaged
        context.setState(new CancelledState());
        System.out.println("Order has been cancelled.");
    }

    @Override
    public String getStateName() {
        return "PREPARING";
    }
}

class OutForDeliveryState implements OrderState {
    @Override
    public void next(OrderContext context) {
        context.setState(new DeliveredState());
        System.out.println("Order has been delivered.");
    }

    @Override
    public void cancel(OrderContext context) {
        // STATE SANITIZATION RULE: Guard claw blocks cancellations once on transit vehicles
        System.out.println("Cannot cancel. Order is out for delivery.");
    }

    @Override
    public String getStateName() {
        return "OUT_FOR_DELIVERY";
    }
}

class DeliveredState implements OrderState {
    @Override
    public void next(OrderContext context) {
        // TERMINAL STATUS GUARD: Endpoint reached, can go no further
        System.out.println("Order is already delivered.");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("Cannot cancel a delivered order.");
    }

    @Override
    public String getStateName() {
        return "DELIVERED";
    }
}

class CancelledState implements OrderState {
    @Override
    public void next(OrderContext context) {
        // TERMINAL STATUS GUARD: Cancelled tracks cannot rejoin active distribution runs
        System.out.println("Cancelled order cannot move to next state.");
    }

    @Override
    public void cancel(OrderContext context) {
        System.out.println("Order is already cancelled.");
    }

    @Override
    public String getStateName() {
        return "CANCELLED";
    }
}

// ============================================================================
// 4. CLIENT INTERACTION
// ============================================================================
public class Main {
    public static void main(String[] args) {
        OrderContext order = new OrderContext();

        System.out.println("Current State: " + order.getCurrentState());

        // Drive structural sequence
        order.next();  // ORDER_PLACED -> PREPARING
        order.next();  // PREPARING -> OUT_FOR_DELIVERY
        
        // Asserting validation guards
        order.cancel(); // Evaluates against OutForDeliveryState logic -> Blocked!
        
        order.next();  // OUT_FOR_DELIVERY -> DELIVERED
        order.cancel(); // Evaluates against DeliveredState logic     -> Blocked!

        System.out.println("Final State: " + order.getCurrentState());
    }
}