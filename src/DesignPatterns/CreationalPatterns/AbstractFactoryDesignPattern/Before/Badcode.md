# Problem Analysis: Hardcoded Object Creation

## 1. Tight Coupling

The `CheckoutService` is directly dependent on concrete classes like `RazorpayGateway` and `GSTInvoice`.

- **The Issue:** The high-level business logic is "aware" of low-level implementation details.
- **The Impact:** You cannot change a provider without touching the core checkout logic.

## 2. Violation of Open/Closed Principle (OCP)

The code is not "closed for modification."

- **The Issue:** Every time you want to add a new payment method (e.g., Stripe, PayPal), you must open `CheckoutService.java` and add another `if-else` or `switch` block.
- **The Impact:** Frequent changes to a core service class increase the risk of introducing bugs in existing logic.

## 3. Lack of Object "Families"

The Abstract Factory pattern is specifically designed to create **families of related objects**.

- **The Issue:** In the current code, the `PaymentGateway` and `Invoice` are created independently. There is no structural guarantee that they "belong" together.
- **The Scenario:** If you expand to the USA, you need a `StripeGateway` AND a `USATaxInvoice`. In the bad code, nothing stops the system from accidentally pairing a `RazorpayGateway` (India) with a `USATaxInvoice` (USA), which would be a business logic error.

## 4. Maintenance Rigidity

As the number of regions and providers grows, the `checkOut` method will become a massive "God Method" filled with complex conditional logic to decide which objects to instantiate.

## Summary of the "Smell"

When you see **conditional logic (`if/else`) being used to decide which concrete class to instantiate**, it is a strong signal that you should delegate that responsibility to a **Factory**.
