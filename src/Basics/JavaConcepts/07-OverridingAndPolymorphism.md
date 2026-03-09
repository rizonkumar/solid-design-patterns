# 7. Method overriding and polymorphism

## Method overriding

**Overriding** means a subclass (or a class that implements an interface) provides **its own implementation** of a method that already exists in the parent or interface. The method signature (name and parameters) stays the same; only the body (behavior) changes.

Use **`@Override`** above the method so the compiler checks that you’re really overriding something; if the parent/interface changes, you get an error instead of silently adding a new method.

## Polymorphism (simple terms)

**Polymorphism** = “one type, many forms.” You write code against a **single type** (usually an interface or parent class), but at **runtime** the actual object can be any implementation. The right method (the overridden one) is called automatically.

So: variable type is `PaymentMethod`, but the object might be `CreditCard` or `UPI`. When you call `paymentMethod.pay(100)`, Java runs the implementation of the **actual** object (e.g. `CreditCard.pay` or `UPI.pay`).

## Example

```java
// Type is the interface
PaymentMethod pm1 = new CreditCard("4111");
PaymentMethod pm2 = new UPI("user@bank");

// Same call, different behavior — that's polymorphism
pm1.pay(100);  // CreditCard's pay() runs
pm2.pay(100);  // UPI's pay() runs

// Processor doesn't care which implementation it gets
public void processPayment(PaymentMethod method, double amount) {
    method.pay(amount);  // Works for CreditCard, UPI, PayPal, etc.
}
```

Overriding: each class has its own `pay(double)` implementation. Polymorphism: `processPayment` depends only on “something that can pay,” not on a specific class.

## Where you see it in this repo

- **OCP:** `PaymentProcessor.processPayment(PaymentMethod, double)` — polymorphism over `PaymentMethod`; each payment type overrides `pay()`.
- **DIP:** `NotificationService` holds `NotificationChannel` and calls `send()` — polymorphic over Email/SMS.
- **Factory:** `Logistics logistics = LogisticsFactory.getLogistics(mode); logistics.send();` — polymorphic over Road/Air.
- **LSP:** `readAnyFile(ReadableFile file)` and `file.read()` — polymorphic over `ReadOnlyFile` and `WritableFile` (both are `ReadableFile`).
- **@Override** is used on every interface implementation and on `clone()` in the Prototype pattern.
