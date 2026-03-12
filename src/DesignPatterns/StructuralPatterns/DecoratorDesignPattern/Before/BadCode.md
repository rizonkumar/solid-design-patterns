### The Class Explosion Problem

The current design relies on **Static Inheritance** to handle object variations. This approach is highly inefficient for systems where features (like pizza toppings) can be combined in numerous ways.

---

## 1. Key Issues in this Design

### Class Explosion (Combinatorial Nightmare)

- **The Issue**: To support every possible combination of toppings, you are forced to create a new class for every permutation (e.g., `CheeseOlivePizza`, `CheeseStuffedPizza`, `CheeseOliveStuffedPizza`).
- **The Impact**: If you have $N$ toppings, you potentially need $2^N$ classes. Adding just one more topping (like "Mushroom") would require creating several new combination classes to remain consistent.

### Violation of the Single Responsibility Principle (SRP)

- **The Issue**: Subclasses like `CheeseOliveStuffedPizza` are burdened with the logic of multiple independent features at once.
- **The Impact**: The code becomes difficult to maintain because the logic for "Cheese" is scattered across every subclass that includes it.

### Static Nature of Inheritance

- **The Issue**: Inheritance is defined at compile-time.
- **The Impact**: You cannot take an existing `PlainPizza` object and "add" cheese to it at runtime based on user input. You would have to discard the old object and create a brand new `CheesePizza` instance.

### Rigid and Brittle Hierarchy

- **The Issue**: The deep inheritance tree makes the system fragile.
- **The Impact**: If the price or logic for the base `PlainPizza` changes, it ripples down through dozens of subclasses, increasing the risk of regression bugs.

---

## 2. Structural Analysis

| Component                     | Status     | Observation                                                                 |
| ----------------------------- | ---------- | --------------------------------------------------------------------------- |
| **Base Class** (`PlainPizza`) | Overloaded | Acts as the root for an unmanageable tree.                                  |
| **Subclasses**                | Redundant  | Most subclasses only exist to represent a specific combination of features. |
| **Extension**                 | Hardcoded  | New features require manual creation of multiple combination classes.       |

---

## 3. Summary of the "Smell"

When you see a **deep inheritance hierarchy** where subclasses are named after **combinations of features** (e.g., `FeatureAWithFeatureB`), it is a clear sign of **Class Explosion**. The **Decorator Pattern** solves this by using **Composition** over Inheritance, allowing you to "wrap" behaviors dynamically.
