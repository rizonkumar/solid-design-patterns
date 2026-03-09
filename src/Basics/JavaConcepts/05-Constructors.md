# 5. Constructors

## What is a constructor?

A **constructor** is a block of code that runs **when you create an object** with `new ClassName(...)`. It has the same name as the class and no return type. It’s used to set up the object’s initial state (assign fields, validate inputs, etc.).

## Types you’ll see

1. **Default constructor** — no parameters. If you don’t write any constructor, Java provides one (no-arg). If you add any other constructor, the default is not provided unless you write it.
2. **Parameterized constructor** — takes arguments (e.g. `public NotificationService(NotificationChannel channel)`). Lets callers pass in dependencies or initial values.
3. **Private constructor** — prevents anyone from doing `new MyClass()` from outside. Used in **Singleton** (only one instance) or in **Builder** (only the builder can construct the object).

## Example

```java
// Parameterized: dependency injected from outside (DIP)
public class NotificationService {
    private NotificationChannel notificationChannel;

    public NotificationService(NotificationChannel channel) {
        this.notificationChannel = channel;
    }

    public void notify(String msg) {
        notificationChannel.send(msg);
    }
}
```

```java
// Private constructor: no one can do 'new JudgeAnalytics()'
public class JudgeAnalytics {
    private static JudgeAnalytics instance;

    private JudgeAnalytics() {
        // Setup once
    }

    public static JudgeAnalytics getInstance() {
        if (instance == null) {
            instance = new JudgeAnalytics();
        }
        return instance;
    }
}
```

- First: constructor **injects** the `NotificationChannel` (dependency injection).
- Second: constructor is **private** so only `getInstance()` can create the single instance (Singleton).

## Where you see it in this repo

- **DIP GoodCode:** `NotificationService(NotificationChannel channel)` — constructor injection.
- **Singleton:** `JudgeAnalytics()` is private; object created only inside `getInstance()`.
- **Builder:** `BurgerMeal(BurgerMealWithBuilder builder)` is private; only the static nested builder class calls it via `build()`.
- **Most classes:** Parameterized constructors to set `amount`, `cardNo`, `observers`, etc.
