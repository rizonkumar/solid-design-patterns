# Observer Pattern — Short Notes

## Definition

The **Observer Pattern** is a behavioral design pattern where:
> An object (Subject) notifies multiple dependent objects (Observers) automatically whenever its state changes.

It allows a **one-to-many relationship** between objects without tight coupling.

---

## Key Components

1. **Subject**
    - Holds some state (e.g., temperature).
    - Maintains a list of observers.
    - Notifies observers when the state changes.

2. **Observer**
    - Gets updated when the subject changes.
    - Reacts based on the new value (e.g., displaying temperature).

---

## Why Use This Pattern

- Reduces tight coupling between objects.
- Makes it easy to add/remove observers at runtime.
- Improves flexibility and scalability.
- Suitable for event-based or real-time update systems.

---

## Real-World Examples

- Weather monitoring systems
- Stock price tickers
- Notification systems
- UI event listeners (button click → multiple handlers)