# OOP — Payment-method example

This folder illustrates **object-oriented programming** using a small payment system: an interface, an abstract class, concrete payment types, a service that uses them, and a client.

## What’s in this folder

| File                                                  | Role                                                                                                          |
| ----------------------------------------------------- | ------------------------------------------------------------------------------------------------------------- |
| **PaymentMethod.java**                                | Interface (contract: `pay()`).                                                                                |
| **Card.java**                                         | Abstract class implementing `PaymentMethod`; shared state (cardNo, userName) and getters.                     |
| **CreditCard.java**, **DebitCard.java**, **Upi.java** | Concrete implementations (extend `Card` or implement `PaymentMethod`).                                        |
| **PaymentService.java**                               | Holds payment methods (e.g. in a `HashMap`) and calls `pay()` on the selected one (polymorphism).             |
| **Wallet.java**                                       | Optional; composition / “has-a” example.                                                                      |
| **Client.java**                                       | Entry point: creates `PaymentService`, adds payment methods, calls `makePayment(...)`. Run via `Client.main`. |
| **Notes.md**                                          | Detailed OOP notes.                                                                                           |
| **CompositionVsInheritance.md**                       | When to prefer composition over inheritance.                                                                  |

## Concepts used here

- **Interface** — `PaymentMethod`
- **Implements** — `Card implements PaymentMethod`; concrete classes implement or extend
- **Abstract class & inheritance** — `Card`; `CreditCard` / `DebitCard` extend it and use `super()`
- **Encapsulation** — `private` fields in `Card`, access via getters
- **Polymorphism** — `PaymentService.makePayment()` calls `pay()` on whatever implementation was added

For short explanations and more examples of these ideas, see **[../JavaConcepts/](../JavaConcepts/)** (interfaces, implements, abstract classes, encapsulation, polymorphism).

## How to run

Run `Client`: it adds a few payment methods and calls `makePayment("RizonUPI")`. Use your IDE’s “Run” on `Client` or, from the project root:

```bash
javac -d out src/Basics/OOP/*.java && java -cp out Basics.OOP.Client
```
