# Dependency Inversion Principle (DIP) — Good Code Explanation

## Principle (restated)

The **Dependency Inversion Principle** says:

- High-level modules should not depend on low-level modules. Both should depend on **abstractions**.
- Abstractions should not depend on details. Details should depend on **abstractions**.

In practice this means: the core/business logic should refer to interfaces or abstract types, not concrete
implementations. Concrete implementations implement those interfaces.

---

## What the Good Code Does

### Key components

- `NotificationChannel` — **abstraction** (interface) that defines `void send(String msg)`.
- `EmailService` and `SMSService` — **concrete implementations** of `NotificationChannel`.
- `NotificationService` — **high-level module** that depends on `NotificationChannel` (the abstraction), not on concrete
  services.
- `Main` — composes `NotificationService` with a concrete implementation (passes `new EmailService()` or
  `new SMSService()`).

### Code flow (conceptual)

1. `Main` creates a concrete `NotificationChannel` (e.g., `new EmailService()`).
2. `Main` passes that implementation into `NotificationService` via the constructor.
3. `NotificationService` calls `notificationChannel.send(msg)`.
4. Concrete implementation executes the actual sending logic.

This is constructor (dependency) injection: the dependency is provided to the consumer rather than created inside it.

---

## How this satisfies DIP

1. **High-level depends on abstraction**
    - `NotificationService` refers to `NotificationChannel`, not `EmailService` or `SMSService`. The business logic (
      notify) deals with the abstraction only.

2. **Low-level depends on abstraction**
    - `EmailService` and `SMSService` implement `NotificationChannel`. Their behavior conforms to the abstraction.

3. **Swappable implementations**
    - You can pass any `NotificationChannel` to `NotificationService` (WhatsApp, Push, Mock for tests) without changing
      `NotificationService`.

4. **Reduces coupling**
    - `NotificationService` has no knowledge of concrete classes’ internals or constructors. Changes in concrete
      implementations do not require changes in high-level code.

---

## Benefits (detailed)

- **Extensibility / Open-Closed**: Add new channels by implementing `NotificationChannel` — no modification required in
  `NotificationService`.
- **Testability**: In unit tests, inject a mock or stub `NotificationChannel` to verify behavior without sending real
  emails/SMS.
- **Single Responsibility**: `NotificationService` focuses on orchestrating notifications; concrete classes handle
  delivery details.
- **Maintainability**: Changes to transport details (APIs, libraries) are limited to the concrete implementations only.
- **Reuse and Composition**: Same `NotificationService` can be reused with different channels in different contexts.

---

## Design choices and alternatives

- **Constructor injection** (used here) is clear and makes dependencies explicit.
- **Setter injection** or **factory/provider** patterns could be used depending on lifecycle needs.
- For many channels or complex lifecycles, consider a **DI container** or a **factory** to resolve implementations.
- If you need multiple channels at runtime, inject a collection (e.g., `List<NotificationChannel>`) or a
  `NotificationChannelFactory` and iterate to send to each.

---

## Small checklist to recognize DIP compliance

- Does the high-level class depend on an interface/abstraction? ✔
- Are concrete classes implementing that interface rather than the high-level class instantiating them directly? ✔
- Can you add a new implementation without modifying the high-level module? ✔
- Can you easily substitute a mock implementation for tests? ✔

If you answered "yes" to all, your design follows DIP.

---

## Example improvements you might consider later

- Define a `Notifier` interface if you want multiple notification behaviors (e.g., `notify(String msg)` vs
  `sendDirect(String msg, Recipient r)`).
- Use a factory/registry when different channels are selected by configuration or runtime conditions.
- Add logging and error handling at the `NotificationService` level while keeping channel code focused on delivery.
- For async delivery, let implementations return futures/promises or accept callbacks.

---

## Conclusion

The good code demonstrates DIP by inverting dependencies: business logic depends on the `NotificationChannel`
abstraction while concrete senders depend on and implement that abstraction. This leads to a modular, testable, and
easily extensible design.
