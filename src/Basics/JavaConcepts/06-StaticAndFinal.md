# 6. `static` and `final`

## `static`

**`static`** means “belongs to the **class**, not to each object.”

- **Static field:** One shared variable for the whole class (e.g. singleton instance, registry map).
- **Static method:** Called on the class, not on an object — e.g. `Main.main(args)`, `LogisticsFactory.getLogistics("road")`, `JudgeAnalytics.getInstance()`.
- **Static block:** `static { ... }` runs once when the class is loaded (e.g. to fill a registry).

You don’t need `new` to use static members: `ClassName.staticMethod()`, `ClassName.staticField`.

## `final`

**`final`** means “cannot be changed after it’s set.”

- **Final variable:** Assigned once (in constructor or at declaration), then read-only.
- **Final field:** Same for object fields — e.g. `private final String content` in a memento.
- **Final static field:** Constant shared by the whole class — e.g. `private static final Map<String, EmailTemplate> templates`.

Using `final` on fields helps make objects **immutable** or clearly “set once” dependencies.

## Examples

```java
// static method = call without an object
public class LogisticsFactory {
    public static Logistics getLogistics(String mode) {
        if ("road".equals(mode)) return new Road();
        if ("air".equals(mode)) return new Air();
        throw new IllegalArgumentException("Unknown mode: " + mode);
    }
}
// Usage: Logistics l = LogisticsFactory.getLogistics("road");
```

```java
// static block = run once when class loads
private static final Map<String, EmailTemplate> templates = new HashMap<>();

static {
    templates.put("welcome", new WelcomeEmail());
}
```

```java
// final = set once, never reassigned
private final List<Observer> observers = new ArrayList<>();
private final String content;  // Set in constructor, then immutable
```

## Where you see it in this repo

- **`main`:** Every `public static void main(String[] args)` — entry point is static.
- **Factory:** `LogisticsFactory.getLogistics(String)` — static factory method.
- **Singleton:** `JudgeAnalytics.getInstance()` — static; inner holder class is `private static class JudgeAnalyticsHolder` with `private static final JudgeAnalytics INSTANCE`.
- **Prototype registry:** `EmailTemplateRegistry` — static map and static block to fill it; `getTemplate(String)` is static.
- **Builder / Memento / Observer:** `private final` fields for immutable or stable state.
