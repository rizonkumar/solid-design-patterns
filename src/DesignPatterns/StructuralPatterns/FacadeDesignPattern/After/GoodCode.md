### The Facade Pattern Solution

The refactored code implements the **Facade Pattern**, which provides a unified, higher-level interface to a set of interfaces in a subsystem. By introducing the `MovieBookingFacade`, we hide the internal complexities of the movie theater's business logic, making the system significantly easier to use for the client.

---

## 1. Key Improvements

### Simplified Interface

- **The Benefit**: The client (`Main`) no longer needs to manage five different service objects or remember the specific order of the booking workflow.
- **The Impact**: A complex multi-step process is reduced to a single, intuitive method call: `bookMovieTicket()`.

### Loose Coupling (Dependency Management)

- **The Benefit**: The client is now only dependent on the `MovieBookingFacade` class instead of five separate concrete service classes.
- **The Impact**: If the internal subsystems (like `LoyaltyPointsService` or `TicketService`) change their implementation details, the client code remains unaffected.

### Encapsulation of Workflow

- **The Benefit**: The Facade ensures that the booking process follows the correct business sequence (e.g., payment before ticket generation).
- **The Impact**: This prevents bugs caused by clients calling subsystem methods in the wrong order or skipping mandatory steps like sending notifications.

### Better Reusability

- **The Benefit**: The booking logic is centralized in one place.
- **The Impact**: Any other part of the application (like a mobile API or a web controller) can reuse the `MovieBookingFacade` to book a ticket without duplicating the setup code.

---

## 2. Structural Breakdown

| Component                            | Responsibility                                                                                     |
| ------------------------------------ | -------------------------------------------------------------------------------------------------- |
| **Facade** (`MovieBookingFacade`)    | Knows which subsystem classes are responsible for a request and delegates client requests to them. |
| **Subsystems** (Payment, Seat, etc.) | Implement specific functionality; they have no knowledge of the facade and work independently.     |
| **Client** (`Main`)                  | Interacts only with the Facade to perform a high-level task.                                       |

---

## 3. Comparison of "Before" vs "After"

| Feature               | Before (Direct Access)                        | After (Facade Pattern)                                |
| --------------------- | --------------------------------------------- | ----------------------------------------------------- |
| **Client Complexity** | High; must coordinate multiple objects.       | Low; calls one method on one object.                  |
| **Coupling**          | Tightly coupled to all subsystems.            | Loosely coupled via the Facade.                       |
| **Maintainability**   | Hard; changes ripple through all client code. | Easy; changes are isolated within the Facade.         |
| **Law of Demeter**    | Violated; client talks to "strangers".        | Respected; client talks only to its immediate friend. |

---

## 4. Summary of the "Entry Point"

The Facade Pattern acts as a **Help Desk**. Instead of walking through every department in a building to get a task done, you go to one desk, and the clerk there coordinates with all the departments for you.
