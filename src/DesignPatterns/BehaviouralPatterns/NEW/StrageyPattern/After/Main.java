package DesignPatterns.BehavioralPatterns.NEW.StrategyPattern.After;

// ============================================================================
// 1. STRATEGY INTERFACE
// ============================================================================
/**
 * Defines the unified contract for all ride-matching algorithms.
 * The Context class will interact only with this interface, making it
 * decoupled from specific algorithm implementations.
 */
interface MatchingStrategy {
    void match(String riderLocation);
}

// ============================================================================
// 2. CONCRETE STRATEGIES
// ============================================================================
/**
 * Encapsulates standard distance-based proximity calculations.
 */
class NearestDriverStrategy implements MatchingStrategy {

    @Override
    public void match(String riderLocation) {
        System.out.println(
            "Matching with the nearest available driver to " + riderLocation
        );
        // Isolated algorithmic logic goes here
    }
}

/**
 * Encapsulates First-In, First-Out (FIFO) queue logic specialized for airport hubs.
 */
class AirportQueueStrategy implements MatchingStrategy {

    @Override
    public void match(String riderLocation) {
        System.out.println(
            "Matching using FIFO airport queue for " + riderLocation
        );
        // Isolated algorithmic logic goes here
    }
}

/**
 * Encapsulates peak-demand, surge pricing priority distribution algorithms.
 */
class SurgePriorityStrategy implements MatchingStrategy {

    @Override
    public void match(String riderLocation) {
        System.out.println(
            "Matching rider using surge pricing priority near " + riderLocation
        );
        // Isolated algorithmic logic goes here
    }
}

// ============================================================================
// 3. CONTEXT CLASS
// ============================================================================
/**
 * Serves as the orchestration engine. It does not know how to compute
 * matching coordinates; it only knows *when* to execute the strategy.
 */
class RideMatchingService {

    // COMPOSITION OVER INHERITANCE: Maintains a reference to the strategy interface
    private MatchingStrategy strategy;

    /**
     * Constructor Injection: Forces the client to provide a valid baseline behavior
     * upon instantiation, avoiding null pointer issues.
     */
    public RideMatchingService(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Setter Injection: Provides RUNTIME FLEXIBILITY.
     * Allows the application to dynamically shift algorithms on the fly
     * without needing to reconstruct the service object.
     */
    public void setStrategy(MatchingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * DELEGATION: Instead of running an if-else check, the context cleanly
     * delegates the task directly to whichever strategy is currently active.
     */
    public void matchRider(String location) {
        strategy.match(location);
    }
}

// ============================================================================
// 4. CLIENT CODE
// ============================================================================
public class Main {

    public static void main(String[] args) {
        // Scenario A: Initializing service with a localized Airport state
        RideMatchingService rideMatchingService = new RideMatchingService(
            new AirportQueueStrategy()
        );
        rideMatchingService.matchRider("Terminal 1");

        System.out.println("------------------------------------------------");

        // Scenario B: Initializing with a default strategy and changing behaviors dynamically
        RideMatchingService rideMatchingService2 = new RideMatchingService(
            new NearestDriverStrategy()
        );
        rideMatchingService2.matchRider("Downtown");

        // Runtime modification: Switching behavioral execution instantly
        rideMatchingService2.setStrategy(new SurgePriorityStrategy());
        rideMatchingService2.matchRider("Downtown");
    }
}
