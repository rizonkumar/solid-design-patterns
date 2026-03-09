# 12. Dependency injection

## What is dependency injection (DI)?

**Dependency injection** means a class **does not create** its dependencies itself. Instead, they are **passed in** from outside (e.g. via constructor or setter). The class depends on **abstractions** (e.g. an interface), not on concrete classes.

In simple terms: “Give me what I need” instead of “I’ll create what I need.”

## Why use it?

- **Testability:** You can pass a fake or mock implementation in tests.
- **Flexibility:** You can switch implementations (e.g. Email vs SMS) without changing the class that uses them.
- **SOLID (DIP):** High-level code depends on abstractions (interfaces), not on low-level details. The *caller* decides which implementation to inject.

## Example: constructor injection

```java
// High-level service depends on an abstraction (interface)
public class NotificationService {
    private final NotificationChannel notificationChannel;

    // Dependency is INJECTED via constructor — we don't do 'new EmailService()' here
    public NotificationService(NotificationChannel channel) {
        this.notificationChannel = channel;
    }

    public void notify(String msg) {
        notificationChannel.send(msg);
    }
}
```

```java
// Caller (e.g. Main) decides which implementation to use
NotificationChannel email = new EmailService();
NotificationService service = new NotificationService(email);
service.notify("Hello");

// Or swap to SMS without changing NotificationService
NotificationService smsService = new NotificationService(new SMSService());
smsService.notify("Hello");
```

- `NotificationService` doesn’t know about `EmailService` or `SMSService`; it only knows `NotificationChannel`. So it’s **inverted**: the high-level service depends on the interface, and the caller injects the concrete channel.

## Where you see it in this repo

- **DIP GoodCode:** `NotificationService(NotificationChannel channel)` — constructor injection. `Main` creates `EmailService` or `SMSService` and passes it into `NotificationService`. This is the main example of DI in the SOLID section.
