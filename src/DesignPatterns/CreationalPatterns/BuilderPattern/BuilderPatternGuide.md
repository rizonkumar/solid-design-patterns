# Design Pattern: Builder (Creational)

## 1. Overview

The **Builder Pattern** is a creational design pattern that separates the construction of a complex object from its representation. It allows you to create different types and representations of an object using the same construction process.

### Formal Definition

> "Builder pattern builds a complex object step by step. It separates the construction of a complex object from its representation, so that the same construction process can create different representations."

### In Simpler Terms (The Chef Analogy)

Imagine ordering a custom burger. You choose the bun, patty, toppings, and sauces. The chef follows your instructions step-by-step. This is what the Builder Pattern does—it allows specification of parts one at a time, giving you flexibility and control without needing a massive, confusing constructor.

---

## 2. Class Diagram

The following diagram illustrates the relationship between the product and its internal builder.

```mermaid
classDiagram
    class BurgerMeal {
        -String bunType
        -String patty
        -boolean hasCheese
        -List~String~ toppings
        -String side
        -BurgerMeal(builder: BurgerBuilder)
        +toString() String
    }

    class BurgerBuilder {
        -String bunType
        -String patty
        -boolean hasCheese
        -List~String~ toppings
        -String side
        +BurgerBuilder(bunType: String, patty: String)
        +withCheese(hasCheese: boolean) BurgerBuilder
        +withSide(side: String) BurgerBuilder
        +build() BurgerMeal
    }

    BurgerMeal +-- BurgerBuilder : static nested
    BurgerBuilder ..> BurgerMeal : creates

```

---

## 3. Understanding the Problem: "Telescoping Constructors"

When a class has many optional parameters, developers often fall into the **Telescoping Constructor Anti-Pattern**.

### The "Bad" Code (Anti-Pattern)

```java
class BurgerMeal {
    // Problem: To handle all combinations, you end up with "Constructor Bloat"
    public BurgerMeal(String bun, String patty) { ... }
    public BurgerMeal(String bun, String patty, boolean cheese) { ... }
    public BurgerMeal(String bun, String patty, boolean cheese, String side) { ... }
    // This is hard to read, error-prone, and inflexible.
}

```

### Key Issues Identified:

1. **Poor Readability:** `new BurgerMeal("Wheat", "Veg", null, null, false)` is hard to decipher.
2. **Forced Nulls:** Users must explicitly pass `null` for items they don't want.
3. **Maintenance Nightmare:** Adding one field breaks every existing constructor call in the project.
4. **No Immutability:** It is difficult to make fields `final` while also allowing flexible construction.

---

## 4. The Solution: Fluent Builder Pattern

By using a static nested class, we can build the object step-by-step.

```java
import java.util.*;

class BurgerMeal {
    // 1. IMMUTABILITY: Fields are 'final'
    private final String bunType;
    private final String patty;
    private final boolean hasCheese;
    private final List<String> toppings;
    private final String side;

    // 2. PRIVATE CONSTRUCTOR: Prevents direct instantiation
    private BurgerMeal(BurgerBuilder builder) {
        this.bunType = builder.bunType;
        this.patty = builder.patty;
        this.hasCheese = builder.hasCheese;
        this.toppings = builder.toppings;
        this.side = builder.side;
    }

    // 3. STATIC NESTED BUILDER
    public static class BurgerBuilder {
        private final String bunType; // Required
        private final String patty;   // Required
        private boolean hasCheese;    // Optional
        private List<String> toppings;
        private String side;

        public BurgerBuilder(String bunType, String patty) {
            this.bunType = bunType;
            this.patty = patty;
        }

        // 4. FLUENT API: Return 'this' for method chaining
        public BurgerBuilder withCheese(boolean hasCheese) {
            this.hasCheese = hasCheese;
            return this;
        }

        public BurgerBuilder withSide(String side) {
            this.side = side;
            return this;
        }

        public BurgerMeal build() {
            return new BurgerMeal(this);
        }
    }
}

```

---

## 5. Why This Is Better

| Aspect                 | Constructor Approach           | Builder Pattern                     |
| ---------------------- | ------------------------------ | ----------------------------------- |
| **Object Readability** | Poor (nulls, long lists)       | Excellent (fluent and expressive)   |
| **Flexibility**        | Low (all-or-nothing setup)     | High (configure only what’s needed) |
| **Maintainability**    | Hard to scale                  | Easy to extend                      |
| **Safety**             | High risk of positional errors | Controlled and safe instantiation   |

---

## 6. When to Use vs. Avoid

### Use When:

- An object has multiple fields, especially many optional ones.
- You want to create **Immutable** objects.
- You want a readable, fluent interface for configuration (e.g., Domain Models).

### Avoid When:

- The class has only 1–2 fields (overkill).
- The object is simple, mutable, or not complex enough to justify the boilerplate.

---

## 7. Real-World Examples

1. **Lombok's `@Builder`:** A Java library that generates all this boilerplate code automatically with a single annotation.
2. **Amazon Cart Configuration:** Building a cart item involves many optional steps (Size, Color, Gift Wrap, Delivery Option) that are best handled via a Builder.
3. **Java's `StringBuilder`:** While slightly different, it follows the concept of constructing a complex String through step-by-step append operations.
