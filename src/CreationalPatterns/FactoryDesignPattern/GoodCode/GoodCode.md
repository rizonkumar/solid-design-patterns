### The Factory Pattern Solution

The refactored code implements the **Simple Factory Pattern**. By centralizing object creation in the `LogisticsFactory`, we have decoupled the `LogisticsService` from specific implementations like `Air` or `Road`.

---

## 1. Key Improvements

### Separation of Concerns

- **The Benefit:** The `LogisticsService` no longer needs to know _how_ to create a transport object; it only needs to know _how to use_ one.
- **The Result:** The business logic (sending) and the creation logic (instantiating) are handled by separate classes.

### Encapsulation of "New"

- **The Benefit:** The `new` keyword is restricted to the Factory.
- **The Impact:** If the constructor of the `Air` class changes tomorrow, you only update it in one place (the Factory) instead of everywhere you use it.

### Error Handling & Validation

- **The Benefit:** The Factory provides a centralized place to validate input.
- **The Safety:** By throwing an `IllegalArgumentException` for unknown modes, we prevent the system from running with an undefined or null state.

---

## 2. Structural Breakdown

| Component                              | Responsibility                                                                     |
| -------------------------------------- | ---------------------------------------------------------------------------------- |
| **Product Interface** (`Logistics`)    | Defines the common contract for all transport types.                               |
| **Concrete Products** (`Air`, `Road`)  | Specific implementations of the interface with their own unique logic.             |
| **Factory Class** (`LogisticsFactory`) | Contains the logic to decide which concrete product to instantiate based on input. |
| **Client** (`LogisticsService`)        | Requests an object from the factory and interacts with it via the interface.       |

---

## 3. Comparison of "Before" vs "After"

| Feature         | Before (Hardcoded)                                   | After (Factory Pattern)                        |
| --------------- | ---------------------------------------------------- | ---------------------------------------------- |
| **Dependency**  | Depends on `Air` and `Road` classes.                 | Depends on `Logistics` interface.              |
| **Maintenance** | High risk; breaking changes occur when adding modes. | Low risk; changes are isolated to the Factory. |
| **Testing**     | Difficult to mock concrete objects.                  | Easy to mock the interface for unit tests.     |
| **Flexibility** | Rigid `if-else` inside service.                      | Clean, delegatory call to a Factory.           |

---
