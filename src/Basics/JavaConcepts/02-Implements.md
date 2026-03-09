# 2. Implementing interfaces (`implements`)

## What does `implements` mean?

When a class **implements** an interface, it **promises** to provide a concrete implementation (method body) for **every** method declared in that interface. The compiler checks this: if you forget a method, you get a compile error.

## Simple terms

- **Interface** = list of method signatures (name + parameters, no body).
- **Implements** = “I fulfill this contract by writing the code for each of those methods.”

A class can implement **multiple** interfaces (e.g. `class MultiMachine implements Printer, Scanner, Copier`).

## Example

```java
public interface PaymentMethod {
    void pay(double amount);
}

// This class MUST have a method: void pay(double amount)
public class CreditCard implements PaymentMethod {
    private String cardNumber;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " using card " + cardNumber);
    }
}
```

- `PaymentMethod` says: “You must have `pay(double amount)`.”
- `CreditCard` implements `PaymentMethod`, so it **must** define `pay(double amount)`. The `@Override` annotation marks that this method is the implementation of the interface method.

## Where you see it in this repo

- **OCP:** `CreditCard`, `DebitCard`, `UPI`, `Paypal` all implement `PaymentMethod`.
- **DIP:** `EmailService`, `SMSService` implement `NotificationChannel`.
- **ISP:** `SimplerPrinter` implements `Printer`; `MultiPurposeMachine` implements `Printer`, `Scanner`, and `Copier`.
- **Factory:** `Road`, `Air` implement `Logistics`.
- **Prototype:** `WelcomeEmail` implements `EmailTemplate`.
