# Composition vs Inheritance

## When to use which

- **Inheritance** (“is-a”): Use when the subclass is a **specialization** of the base type and you need polymorphism (e.g. `Dog` is an `Animal`, `CreditCard` is a `PaymentMethod`).
- **Composition** (“has-a”): Use when one object **uses** another as a part of its behavior, without being a subtype (e.g. `Car` has an `Engine`, `PaymentService` has a `PaymentMethod`).

## Prefer composition when

- You want to **reuse behavior** without exposing the full API of the inner object.
- You need to **swap implementations** at runtime (e.g. inject different payment methods).
- The relationship is **has-a** rather than **is-a** (e.g. “a car has an engine”, not “a car is an engine”).
- You want to avoid **deep inheritance** and fragile base-class changes.

## Prefer inheritance when

- There is a clear **is-a** relationship and **subtype polymorphism** is needed.
- Subclasses truly **extend** the base type’s contract without weakening it (Liskov Substitution).
- You need **override** and **override-only** extension points.

## Example (composition)

```java
// Composition: PaymentService HAS-A PaymentMethod (injected)
class PaymentService {
    private final PaymentMethod method;

    PaymentService(PaymentMethod method) {
        this.method = method;
    }

    void pay(double amount) {
        method.pay(amount);
    }
}
```

## Example (inheritance)

```java
// Inheritance: CreditCard IS-A PaymentMethod
class CreditCard implements PaymentMethod {
    @Override
    void pay(double amount) { /* ... */ }
}
```

## Summary

| Aspect       | Inheritance                        | Composition                   |
| ------------ | ---------------------------------- | ----------------------------- |
| Relationship | Is-a                               | Has-a                         |
| Flexibility  | Fixed at compile time              | Can swap at runtime           |
| Coupling     | Tighter (subclass depends on base) | Looser (depends on interface) |
| Use when     | True subtype, polymorphism         | Reuse + encapsulation         |
