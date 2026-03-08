### Violation in the Bad Code

In the given example:

- `NotificationService` (high-level module) directly depends on concrete classes `EmailService` and `SMSService` (
  low-level modules).
- This creates **tight coupling** — if the implementation of `EmailService` or `SMSService` changes,
  `NotificationService` must also change.
- Adding a new service (like WhatsApp) would require modifying `NotificationService`, which violates the **Open/Closed
  Principle** as well.

### Problems with This Design

- Hard to extend or replace services.
- Difficult to test because dependencies are tightly bound.
- Violates DIP since high-level logic depends on low-level implementations instead of abstractions.
