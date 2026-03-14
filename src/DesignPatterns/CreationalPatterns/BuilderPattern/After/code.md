# Builder Pattern: Deep Dive

## 1. Core Concepts

The Builder Pattern is a **Creational Design Pattern** used to construct complex objects step-by-step. It separates the construction of an object from its representation.

### Why use it?

- **Eliminates Telescoping Constructors:** You don't need 10 different constructors for different combinations of fields.
- **Readable Code:** Using `.withCheese(true)` is much clearer than just passing `true` in a long list of arguments.
- **Controlled Construction:** You can validate data before the object is actually created.

## 2. Java Specifics Used

### `private final` Fields

By making the fields in the main class `final`, we ensure **Immutability**. Once the `build()` method is called, the `BurgerMeal` cannot be changed. This is a thread-safe and robust practice.

### `static` Nested Class

The Builder is inside the `BurgerMeal` class and marked as `static`.

- **Static:** Allows us to call `new BurgerMeal.BurgerMealWithBuilder()` without having a `BurgerMeal` object first.
- **Nested:** Keeps the builder tightly coupled with the class it builds, making the code easier to find and maintain.

### `private` Constructor

The constructor of the main class is `private`. This forces developers to use the Builder, ensuring that no "half-finished" or "invalid" objects are created.

### `return this;` (Fluent Interface)

Each optional method returns the builder instance itself. This enables **Method Chaining**, making the code look like a natural language sentence.

## 3. Comparison


| Feature             | Telescoping (Bad)                        | Builder (Good)                           |
| ------------------- | ---------------------------------------- | ---------------------------------------- |
| **Optional Params** | Requires `null` or multiple constructors | Just skip the method call                |
| **Readability**     | Hard to tell what each value does        | Explicit method names (e.g., `withSide`) |
| **Immutability**    | Hard to achieve with many params         | Easily achieved with `final` fields      |
