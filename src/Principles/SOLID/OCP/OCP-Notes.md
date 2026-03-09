## OCP - The Open/Closed Principle

The **Open/Closed Principle (OCP)** states that **software entities** (such as classes, modules, and functions) should be:

- **Open for extension** – You should be able to add new functionality or behavior.
- **Closed for modification** – You shouldn’t have to change existing, working code to add that functionality.

---

### In Simpler Terms
You can **extend** a class’s behavior (for example, by creating a new subclass or using composition),  
but you **shouldn’t modify** the existing class directly.

This helps prevent bugs and keeps existing features stable when new features are added.

---

### Example
Imagine you have a `Shape` class and a `AreaCalculator` that calculates areas.

Instead of editing `AreaCalculator` every time you add a new shape (like `Circle`, `Rectangle`, or `Triangle`),  
you design it so that **new shapes can be added without changing the existing code** — just by extending the `Shape` class.

---

### Summary
The **Open/Closed Principle** encourages designing code that is **easy to extend** but **safe from unwanted changes**,  
making your system more **maintainable and scalable**.
