package DesignPatterns.BehavioralPatterns.NEW.StrategyPattern.Before;

/**
 * PROBLEM: Monolithic Control Flow & Violation of OCP.
 * The 'RideMatchingService' is forced to house the details of every single
 * matching algorithm. This class will change constantly.
 */
class RideMatchingService {

    public void matchRider(String riderLocation, String matchingType) {
        /**
         * ISSUE 1: CONDITIONAL EXPLOSION
         * As Uber/Lyft expands, you will add "EV_Priority", "Carpool", "Luxury".
         * This if-else chain will grow infinitely, becoming hard to read and test.
         */
        if (matchingType.equals("NEAREST")) {
            // Complex geolocation/haversine formula logic would sit here
            System.out.println(
                "Matching rider at " + riderLocation + " with nearest driver."
            );
        }
        /**
         * ISSUE 2: ZERO ISOLATION
         * A bug introduced inside the "SURGE_PRIORITY" calculation code could
         * accidentally crash the entire method, breaking the "AIRPORT_QUEUE" service.
         */
        else if (matchingType.equals("SURGE_PRIORITY")) {
            // Complex surge multiplier logic would sit here
            System.out.println(
                "Matching rider at " +
                    riderLocation +
                    " based on surge pricing priority."
            );
        } else if (matchingType.equals("AIRPORT_QUEUE")) {
            // FIFO Queue management logic would sit here
            System.out.println(
                "Matching rider at " + riderLocation + " from airport queue."
            );
        } else {
            System.out.println("Invalid matching strategy provided.");
        }
    }
}

// Client Code
public class Main {

    public static void main(String[] args) {
        RideMatchingService service = new RideMatchingService();

        // ISSUE 3: MAGIC STRINGS
        // Passing matching types as raw strings ("NEAREST") is error-prone.
        // A typo like "Nearest" will fail silently or break execution.
        service.matchRider("Downtown", "NEAREST");
        service.matchRider("City Center", "SURGE_PRIORITY");
        service.matchRider("Airport Terminal 1", "AIRPORT_QUEUE");
    }
}
