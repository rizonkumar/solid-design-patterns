### The Decorator Pattern Solution

The refactored code implements the **Decorator Pattern**, allowing for the dynamic addition of responsibilities to objects without modifying their structure. By using **Composition** over Inheritance, we avoid the "Class Explosion" that occurs when trying to represent every possible combination of features.

---

## 1. Key Improvements

### dynamic Behavior Addition

- **The Benefit**: You can add toppings (Extra Cheese, Olives, Stuffed Crust) to a base pizza (Margherita, Plain) at runtime based on user selection.
- **The Impact**: The system is highly flexible; you don't need a `MargheritaWithCheeseAndOlives` class because the behavior is "wrapped" around the base object.

### Open/Closed Principle (OCP)

- **The Benefit**: To add a new topping, such as "Mushroom," you only need to create one new `Mushroom` decorator class.
- **The Impact**: Existing classes remain untouched, reducing the risk of introducing bugs into the base pizza or existing topping logic.

### Single Responsibility Principle (SRP)

- **The Benefit**: Each decorator class is only responsible for its own unique feature (e.g., `ExtraCheese` only handles cheese cost and description).
- **The Impact**: This makes the code significantly easier to maintain, test, and debug compared to having one massive class trying to manage multiple combinations.

---

## 2. Structural Breakdown

| Component                                         | Responsibility                                                                                           |
| ------------------------------------------------- | -------------------------------------------------------------------------------------------------------- |
| **Component Interface** (`Pizza`)                 | Defines the standard contract (methods) for both base objects and decorators.                            |
| **Concrete Components** (`MargheritaPizza`)       | The basic objects that can have additional responsibilities attached to them.                            |
| **Base Decorator** (`PizzaDecorator`)             | An abstract class that implements the interface and maintains a reference ("HAS-A") to a `Pizza` object. |
| **Concrete Decorators** (`ExtraCheese`, `Olives`) | Classes that extend the base decorator and add specific behavior by overriding methods.                  |

---

## 3. Comparison of "Before" vs "After"

| Feature               | Before (Inheritance)                                   | After (Decorator Pattern)                           |
| --------------------- | ------------------------------------------------------ | --------------------------------------------------- |
| **Number of Classes** | Increases exponentially ($2^N$ for $N$ toppings).      | Increases linearly ($1$ class per new topping).     |
| **Flexibility**       | Static; combinations are fixed at compile-time.        | Dynamic; combinations are built at runtime.         |
| **Maintenance**       | High; changing one base class affects many subclasses. | Low; features are isolated in their own decorators. |
| **Code Reuse**        | Low; logic for "Cheese" is repeated in many classes.   | High; "Cheese" logic exists in exactly one place.   |

---

## 4. Summary of the "Wrap"

The Decorator Pattern works like a **Russian Nesting Doll**. You start with a small base (the Pizza), and you keep wrapping it in larger shells (the Decorators). Each shell adds its own "layer" of description and cost before asking the inner doll for its value.
