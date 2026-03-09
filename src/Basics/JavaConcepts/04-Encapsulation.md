# 4. Encapsulation

## What is encapsulation?

**Encapsulation** means keeping an object’s **data (state) hidden** from the outside and only allowing access through controlled methods. In Java we do this mainly with:

- **`private`** fields — only the same class can read/write them directly.
- **Getters** — methods that return the value of a field (e.g. `getContent()`).
- **Setters** — methods that change the value of a field (e.g. `setContent(String s)`), if you want to allow changes.

So “encapsulation” = hide data, expose behavior through methods.

## Why use it?

- **Control:** You can validate or transform values in setters, or make some fields read-only (only getter, no setter).
- **Flexibility:** You can change how data is stored inside the class without breaking code that uses the getters/setters.
- **Safety:** External code cannot put the object in an invalid state by setting fields directly.

## Example

```java
public class EditorMemento {
    // Private = no one from outside can touch 'content' directly
    private final String content;

    public EditorMemento(String content) {
        this.content = content;
    }

    // Only way to read it — no setter, so it's immutable after creation
    public String getContent() {
        return content;
    }
}
```

```java
public class NotificationService {
    private NotificationChannel notificationChannel;  // Private dependency

    public NotificationService(NotificationChannel channel) {
        this.notificationChannel = channel;
    }

    public void notify(String msg) {
        notificationChannel.send(msg);  // Use the dependency, don't expose it
    }
}
```

- In the first example, `content` is private and only readable via `getContent()`; no setter, so the memento cannot be changed.
- In the second, the dependency is stored in a private field and used only inside the class; callers don’t need to know or touch it.

## Where you see it in this repo

- **Builder:** `BurgerMeal` has `private final` fields; built only via the builder, no direct field access.
- **Singleton:** `JudgeAnalytics` holds state in private fields, access via getters.
- **Observer:** `List<Observer> observers` is private; subject notifies internally.
- **Memento:** `EditorMemento` has `private final String content` and only `getContent()`.
- **SOLID:** DIP/OCP/SRP examples use private fields and constructor or method parameters to pass dependencies and data.
