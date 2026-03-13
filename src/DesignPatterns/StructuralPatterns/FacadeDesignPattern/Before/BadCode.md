### The Complex Client Logic Problem

The current design forces the client to manage the entire lifecycle and coordination of multiple subsystems. This results in a "chatty" interface where the client must perform a ritualistic sequence of calls just to achieve a single business goal.

---

## 1. Key Issues in this Design

### Tight Coupling (System Fragility)

- **The Issue**: The `Main` class is directly dependent on five separate concrete classes: `PaymentService`, `SeatReservationService`, `NotificationService`, `LoyaltyPointsService`, and `TicketService`.
- **The Impact**: If any of these services change their method signatures or constructors, the client code breaks immediately. The client "knows" too much about the internal workings of the theater system.

### Violation of the "Law of Demeter"

- **The Issue**: The client is talking to "strangers" (internal subsystems) that it shouldn't need to know about.
- **The Impact**: Instead of asking for a "Booking," the client is manually manipulating the payment, the seating, and the printer. This creates a brittle architecture where internal changes leak into the high-level logic.

### Poor Reusability and Maintenance

- **The Issue**: The logic for booking a ticket is scattered and duplicated in the `Main` method.
- **The Impact**: If you wanted to add a "Movie Booking" feature to a mobile app or a website, you would have to copy-paste this 10-line sequence. If the business logic changes (e.g., loyalty points should only be added _after_ the ticket is generated), you must find and fix every single duplicate instance in the codebase.

### High Cognitive Load

- **The Issue**: A developer using these services must understand the exact order of operations to avoid bugs (e.g., accidentally generating a ticket before confirming payment).
- **The Impact**: This increases the chance of human error and makes the codebase harder for new team members to navigate.

---

## 2. Structural Analysis

| Component           | Status           | Observation                                                                                     |
| ------------------- | ---------------- | ----------------------------------------------------------------------------------------------- |
| **Client** (`Main`) | **Overburdened** | Handles coordination, instantiation, and execution of five subsystems.                          |
| **Subsystems**      | **Exposed**      | All internal services are visible to the outside world, increasing the "surface area" for bugs. |
| **Workflow**        | **Unprotected**  | There is no single "entry point" to ensure the booking process is followed correctly.           |

---

## 3. Summary of the "Smell"

When a client needs to interact with a **large number of diverse classes** to perform a **single task**, you have a complexity bottleneck. The **Facade Pattern** solves this by providing a "Simplified Entry Point" (a single class) that orchestrates these calls behind the scenes.
