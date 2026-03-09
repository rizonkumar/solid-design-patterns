# 1. Interfaces (Simple Terms)

## What is an interface?

An **interface** is a **contract**. It only lists method names (and their parameters). It does **not** contain the actual code (body) for those methods. Any class that “implements” the interface must provide the code for every method in that contract.

Think of it like a job description: “Must be able to do X and Y.” The interface says _what_ must be done; each implementing class decides _how_.

## Why use it?

- So you can **program to the contract**, not to a specific class. Your code depends on “something that can do X,” not “this one class.”
- So you can **swap implementations** without changing the rest of the code (e.g. different payment methods, different notification channels).

## Simple example

```java
// Contract: "Anyone who implements this must have a send method."
public interface NotificationChannel {
    void send(String message);
}

// One implementation
public class EmailService implements NotificationChannel {
    @Override
    public void send(String message) {
        System.out.println("Email: " + message);
    }
}

// Another implementation
public class SMSService implements NotificationChannel {
    @Override
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}
```

- `NotificationChannel` does not have a body for `send`—it’s just the contract.
- `EmailService` and `SMSService` both “sign” the contract by `implements NotificationChannel` and provide their own `send` implementation.

## Where you see it in this repo

- **SOLID DIP:** `NotificationChannel` — `NotificationService` depends on the interface, not on Email or SMS.
- **SOLID OCP:** `PaymentMethod` — many payment types implement the same `pay(amount)` contract.
- **Factory pattern:** `Logistics` — Road/Air are different implementations of the same contract.
- **Prototype:** `EmailTemplate` — contract for clone, setContent, send.
