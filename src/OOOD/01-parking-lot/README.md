# Parking Lot System Design

## Requirements Gathering

Imagine you’re arriving at a busy parking lot, eager to park your car. At the entrance, you’re issued a ticket. You then drive in, find a spot suited to your vehicle’s size, and park. Later, when you prepare to leave, you present your ticket at the exit, the system calculates your fee, and the spot is freed up for the next vehicle. Behind the scenes, the parking lot is assigning spots based on vehicle size, recording entry and exit times, and updating availability for new arrivals. Now, let’s design a parking lot system that handles all this.

## Requirements Clarification

**Candidate:** What types of vehicles are supported by the parking lot?  
**Interviewer:** Three types of vehicles should be supported: motorcycles, cars, and trucks.

**Candidate:** What parking spot types are available in the parking lot?  
**Interviewer:** The parking lot supports three types of parking spots: compact, regular spots, and oversized.

**Candidate:** How does the system determine which spot a vehicle should park in?  
**Interviewer:** The system assigns spots based on the size of the vehicle, ensuring an appropriate fit.

**Candidate:** Are parking tickets issued to vehicles upon entry and charged at the exit?  
**Interviewer:** Yes, a ticket is issued with vehicle details and entry time when a vehicle enters. On exit, the system calculates the fee based on duration and vehicle size, then marks the spot as vacant.

**Candidate:** How are parking fees calculated?  
**Interviewer:** Fees are based on parking duration and vehicle size, with rates varying depending on the time of day.

## Requirements

Here are the key functional requirements we’ve identified:
- The parking lot has multiple parking spots, including compact, regular, and oversized spots.
- The parking lot supports parking for motorcycles, cars, and trucks.
- Customers can park their vehicles in spots assigned based on vehicle size.
- Customers receive a parking ticket with vehicle details and entry time at the entry point and pay a fee based on duration, vehicle size, and time of day at the exit point.

Below are the non-functional requirements:
- The system must scale to support large parking lots with many spots and vehicles.
- The system must reliably track spot assignments and ticket details to ensure accurate operations.

## Identify Core Objects

- **Vehicle**: This object represents a vehicle that needs a spot. It encapsulates details like the license plate and size (small for motorcycles, medium for cars, large for trucks), serving as the foundation for spot assignment and fee calculation.
- **ParkingSpot**: This object models an individual parking spot in the parking lot. It’s the physical space where a `Vehicle` parks, ensuring only appropriately sized vehicles can park based on its capacity.
- **Ticket**: This object represents a parking ticket issued when a `Vehicle` enters the parking lot. It stores critical details, including the ticket ID, the associated `Vehicle`, the assigned `ParkingSpot`, and entry time, which are later used to calculate fees and free up spots upon exit.
- **ParkingManager**: This object oversees the parking lot’s spot allocation, managing the assignment, lookup, and release of `ParkingSpot` instances. It ensures a `Vehicle` gets the right spot by checking availability based on size, and updates the system when vehicles leave, keeping parking operations smooth and efficient.
- **ParkingLot**: This acts as a facade, providing a central interface to manage the system’s key functionalities: vehicle entry, spot assignment, ticketing, and fee calculation. It keeps its logic lightweight by delegating tasks such as spot allocation to the `ParkingManager`, fee computation to a `FareCalculator` class, and coordinating the flow of vehicles in and out without handling the details.

*Design choice:* We chose these five objects to separate concerns. `Vehicle` and `ParkingSpot` define the core physical entities, `Ticket` tracks sessions, `ParkingManager` handles allocation, and `ParkingLot` coordinates as a facade.

## Design Class Diagram

We have modeled the `Vehicle` as an interface to set a standard for all vehicle types. It defines two key methods:
- `getLicensePlate()`: Returns the vehicle’s license plate number.
- `getSize()`: Returns a `VehicleSize` enum (`SMALL`, `MEDIUM`, `LARGE`), indicating the space it occupies.

Concrete classes like `Motorcycle`, `Car`, and `Truck` implement the `Vehicle` interface, each defining its size:
- **Motorcycle**: Small-sized.
- **Car**: Medium-sized.
- **Truck**: Large-sized.

![Vehicle Class Diagram](./assets/vehicle.png)

### ParkingSpot

The `ParkingSpot` interface represents a parking spot in the parking lot system. It captures spot-specific details, such as whether it’s occupied and its size. Concrete parking spot types (`CompactSpot`, `RegularSpot`, and `OversizedSpot`) are implemented as classes that adhere to the `ParkingSpot` interface. These classes bring the interface to life, defining spots for small, medium, and large vehicles, respectively.

The UML diagram below illustrates this structure.

![Parking Spot Class Diagram](./assets/parking_spot.png)

### ParkingManager

The `ParkingManager` is responsible for managing the allocation and tracking of parking spots within the parking lot system. Its primary functions include identifying available parking spaces, assigning the most suitable spot for each vehicle, and maintaining a record of parked vehicles and their locations. These tasks are accomplished through two key methods:

- `parkVehicle(Vehicle vehicle)`: Assigns a spot that matches the vehicle’s size when it arrives.
- `unparkVehicle(Vehicle vehicle)`: Frees up the spot when the vehicle leaves, ensuring the system stays up-to-date.

Here is the representation of the `ParkingManager` class.

![Parking Manager Class Diagram](./assets/parking_manager.png)

### Ticket

The `Ticket` class represents a parking ticket generated when a vehicle enters the parking lot. It keeps track of when a vehicle arrives and leaves, using these times to calculate duration, and links the vehicle to its assigned spot.

Below is the representation of the `Ticket` class.

![Ticket Class Diagram](./assets/ticket.png)

### FareStrategy and FareCalculator

We design the `FareStrategy` interface to establish a standard method for modifying the parking fee, allowing various pricing rules to fit into the system. Its concrete classes handle specific pricing rules:

- `BaseFareStrategy` establishes the base fee using the ticket’s duration and vehicle size.
- `PeakHoursFareStrategy` modifies it based on the time of day.

Since a parking session often involves multiple pricing rules, like duration, size, and time, we design a `FareCalculator` class to coordinate these changes and calculate the final fee. It is designed to determine the cost for each ticket by combining the effects of all applicable strategies (`BaseFareStrategy`, `PeakHoursFareStrategy`), ensuring the system applies the right fee based on how long the vehicle stays, its size, and when it is parked.

This association between `FareStrategy` and `FareCalculator` maintains a structured pricing process, with `FareStrategy` defining the rules and `FareCalculator` pulling them together.

The pricing logic relies on the Strategy Pattern, which enables the system to dynamically select and swap between different rules for calculating parking fees.

The UML diagram below illustrates this structure.

![Fare Strategy Class Diagram](./assets/fare_strategy.png)

### ParkingLot

We design the `ParkingLot` class as the core component of the system to act as a facade, providing a simple interface for managing the parking lot’s key operations. It manages vehicle entry and exit by generating tickets for arrivals, assigning spots through the `ParkingManager`, and calculating fares with the `FareCalculator` when vehicles leave, tying the system’s main functions together.

Below is the representation of this class.

![Parking Lot Class Diagram](./assets/parking_lot.png)

## Adding a New Parking Spot Type

The parking lot system is designed to support multiple parking spot types (e.g., `CompactSpot`, `RegularSpot`, `OversizedSpot`). However, there may be a need to introduce a new type, such as a handicapped parking spot, to accommodate specific requirements like accessibility. The challenge is to extend the system efficiently without modifying existing classes, adhering to the Open-Closed Principle (open for extension, closed for modification).

To achieve this, we can introduce a new `HandicappedSpot` class that implements the existing `ParkingSpot` interface. This approach ensures smooth integration with the system’s spot allocation and management logic, as `ParkingManager` already relies on the `ParkingSpot` interface for handling spots.

```mermaid
classDiagram
    class ParkingSpot {
        <<interface>>
        +boolean isAvailable()
        +void occupy(Vehicle vehicle)
        +void vacate()
        +int getSpotNumber()
        +VehicleSize getSize()
    }
    class CompactSpot {
        -int spotNumber
        -Vehicle vehicle
    }
    class RegularSpot {
        -int spotNumber
        -Vehicle vehicle
    }
    class OversizedSpot {
        -int spotNumber
        -Vehicle vehicle
    }
    class HandicappedSpot {
        -int spotNumber
        -Vehicle vehicle
    }

    ParkingSpot <|.. CompactSpot
    ParkingSpot <|.. RegularSpot
    ParkingSpot <|.. OversizedSpot
    ParkingSpot <|.. HandicappedSpot
```

## Complete Class Diagram

![Complete Class Diagram](./assets/complete_class_diagram.png)
