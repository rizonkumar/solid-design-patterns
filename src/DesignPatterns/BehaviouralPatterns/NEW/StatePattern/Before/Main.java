package DesignPatterns.BehaviouralPatterns.NEW.StatePattern.Before;

import java.util.*;

/**
 * PROBLEM: Fragmented State Transition Mechanics.
 * The 'Order' class is acting as both the data entity and the controller for 
 * every single state rule. It breaks encapsulation principles by explicitly handling
 * string state flags across all of its operations.
 */
class Order {
    // ISSUE 1: MAGIC STRINGS AND RUNTIME FRAGILITY
    // State is managed via string literals. A single typo like "PREPAREING" 
    // will silently break state comparisons without raising compile-time errors.
    private String state;

    public Order() {
        this.state = "ORDER_PLACED";
    }

    /**
     * ISSUE 2: CONDITIONAL SCATTERING
     * For every single action (cancel, refund, ship, assign), you must write 
     * complex if-else conditions checking every permissible state permutation.
     */
    public void cancelOrder() {
        if (state.equals("ORDER_PLACED") || state.equals("PREPARING")) {
            state = "CANCELLED";
            System.out.println("Order has been cancelled.");
        } else {
            System.out.println("Cannot cancel the order now.");
        }
    }

    /**
     * ISSUE 3: OCP VIOLATION & MONOLITHIC SWITCH BLOCKS
     * If you want to introduce a new intermediate state (e.g., "DISPATCHED" or "RETURNED"),
     * you have to modify the switch block here, the conditional loops in cancelOrder(), 
     * and every other state-dependent method. This opens up massive regression risks.
     */
    public void nextState() {
        switch (state) {
            case "ORDER_PLACED":
                state = "PREPARING";
                break;
            case "PREPARING":
                state = "OUT_FOR_DELIVERY";
                break;
            case "OUT_FOR_DELIVERY":
                state = "DELIVERED";
                break;
            default:
                System.out.println("No next state from: " + state);
                return;
        }
        System.out.println("Order moved to: " + state);
    }

    public String getState() {
        return state;
    }
}

class Main {
    public static void main(String[] args) {
        Order order = new Order();
        
        System.out.println("Initial State: " + order.getState());

        order.nextState(); // ORDER_PLACED -> PREPARING
        order.nextState(); // PREPARING -> OUT_FOR_DELIVERY
        order.nextState(); // OUT_FOR_DELIVERY -> DELIVERED

        order.cancelOrder(); // Validates delivery block

        System.out.println("Final State: " + order.getState());
    }
}