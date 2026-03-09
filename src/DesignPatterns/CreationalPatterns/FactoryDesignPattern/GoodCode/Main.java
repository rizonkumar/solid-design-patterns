package DesignPatterns.CreationalPatterns.FactoryDesignPattern.GoodCode;

/**
 * 1. PRODUCT INTERFACE
 * Defines the common behavior for all transport types.
 */
interface Logistics {
    void send();
}

/**
 * 2. CONCRETE PRODUCTS
 * Specific implementations of the Logistics interface.
 */
class Road implements Logistics {
    @Override
    public void send() {
        System.out.println("Sending by road logic");
    }
}

class Air implements Logistics {
    @Override
    public void send() {
        System.out.println("Sending by air logic");
    }
}

/**
 * 3. THE FACTORY
 * Encapsulates the 'new' keyword and instantiation logic.
 * This is the only place where the system decides which class to create.
 */
class LogisticsFactory {
    public static Logistics getLogistics(String mode) {
        // Benefit: We can use ignoreCase to prevent common string typos
        if (mode.equalsIgnoreCase("Air")) {
            return new Air();
        } else if (mode.equalsIgnoreCase("Road")) {
            return new Road();
        }
        
        // Error Handling: Centralized place to handle invalid requests
        throw new IllegalArgumentException("Unknown logistics mode: " + mode);
    }
}

/**
 * 4. THE SERVICE (Client)
 * Now purely focused on THE USE of the object, not the CREATION.
 * It is decoupled from 'Air' and 'Road' classes.
 */
class LogisticsService {
    public void send(String mode) {
        /* Dependency Inversion: The service depends on the Logistics interface, 
           not the concrete Air/Road implementations.
        */
        Logistics logistics = LogisticsFactory.getLogistics(mode);
        logistics.send();
    }
}

// Driver Code
class Main {
    public static void main(String[] args) {
        LogisticsService service = new LogisticsService();
        service.send("Air");
        service.send("Road");
    }
}