# 11. Inner and static nested classes

## What are they?

A class defined **inside** another class is a **nested class**. Two common cases:

1. **Inner class** — non-static. It belongs to an **instance** of the outer class and can access the outer object’s fields. You need an outer instance to create it: `outer.new Inner()`.
2. **Static nested class** — declared with `static`. It does **not** belong to an instance; it’s just grouped with the outer class for organization. You create it without an outer instance: `OuterClass.NestedClass obj = new OuterClass.NestedClass(...)`.

## When to use static nested

- When the nested class doesn’t need to use the outer instance (no access to outer’s non-static fields).
- When you want **one** instance of something per outer *class* (e.g. a singleton holder) or a **builder** that is used to construct the outer class.

## Example: static nested class (Builder)

```java
public class BurgerMeal {
    private final String breadType;
    private final String patty;

    private BurgerMeal(BurgerMealWithBuilder builder) {
        this.breadType = builder.breadType;
        this.patty = builder.patty;
    }

    // Static nested: no outer instance needed. Name suggests it builds BurgerMeal.
    public static class BurgerMealWithBuilder {
        private String breadType;
        private String patty;

        public BurgerMealWithBuilder(String breadType, String patty) {
            this.breadType = breadType;
            this.patty = patty;
        }

        public BurgerMealWithBuilder withCheese(boolean add) {
            // ...
            return this;
        }

        public BurgerMeal build() {
            return new BurgerMeal(this);  // Only BurgerMeal can call its private constructor
        }
    }
}

// Usage: new BurgerMeal.BurgerMealWithBuilder("wheat", "chicken").withCheese(true).build()
```

- `BurgerMealWithBuilder` is **static**, so you don’t need a `BurgerMeal` instance to create it: `new BurgerMeal.BurgerMealWithBuilder(...)`.
- `BurgerMeal`’s constructor is private; only the builder’s `build()` creates a `BurgerMeal`.

## Example: static nested class (Singleton holder)

```java
public class JudgeAnalytics {
    private JudgeAnalytics() {}

    // Holder: loaded only when first used; JVM guarantees thread-safe initialization
    private static class JudgeAnalyticsHolder {
        private static final JudgeAnalytics INSTANCE = new JudgeAnalytics();
    }

    public static JudgeAnalytics getInstance() {
        return JudgeAnalyticsHolder.INSTANCE;
    }
}
```

- The **holder** is a static nested class. It exists only to hold the single `JudgeAnalytics` instance. No one else needs to reference `JudgeAnalyticsHolder`; they only call `getInstance()`.

## Where you see it in this repo

- **BuilderPattern/After:** `BurgerMeal.BurgerMealWithBuilder` — static nested builder.
- **SingletonDesignPattern/After:** `JudgeAnalytics.JudgeAnalyticsHolder` — static nested holder for the singleton instance.
