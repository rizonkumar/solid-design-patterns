package CreationalPatterns.FactoryDesignPattern.BadCode;

// Logistics Interface
interface Logistics {
    void send();
}

// Concrete implementation 1
class Road implements Logistics {
    @Override
    public void send() {
        System.out.println("Sending by road logic");
    }
}

// Concrete implementation 2
class Air implements Logistics {
    @Override
    public void send() {
        System.out.println("Sending by air logic");
    }
}

/**
 * PROBLEM: The "LogisticsService" is doing too much.
 * It handles BOTH the delivery logic AND the creation logic.
 */
class LogisticsService {
    public void send(String mode) {
        // PROBLEM 1: Tight Coupling
        // This service is directly dependent on 'Air' and 'Road' classes.
        // If you want to delete 'Road' or rename it, this service breaks.
        
        // PROBLEM 2: Violation of Open/Closed Principle
        // If we want to add 'Sea' or 'Rail' transport, we MUST modify this 
        // if-else block. This increases the risk of breaking existing code.
        if (mode.equals("Air")) {
            Logistics logistics = new Air(); // 'new' keyword here is a code smell
            logistics.send();
        } else if (mode.equals("Road")) {
            Logistics logistics = new Road();
            logistics.send();
        }
        
        // PROBLEM 3: Lack of Scalability
        // As you add 10 more transport types, this method becomes a giant 
        // "God Method" that is hard to test and maintain.
    }
}

class Main {
    public static void main(String[] args) {
        LogisticsService service = new LogisticsService();
        service.send("Air");
        service.send("Road");
    }
}