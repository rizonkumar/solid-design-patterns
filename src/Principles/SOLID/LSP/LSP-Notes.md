## LSP - The Liskov Substitution Principle

The **Liskov Substitution Principle (LSP)** states that objects of a **superclass** should be replaceable with objects of a **subclass** without altering the correctness of the program.  
It ensures that a subclass can stand in for its parent class and function correctly in any context that expects the parent class.

---

### In Simpler Terms
You should be able to use the **child class** instead of the **parent class**, and the program should still behave correctly.

---

### Example
If you have a class called `Bird` that can fly, and you create a subclass called `Sparrow`, then `Sparrow` should be able to do everything a `Bird` can — including flying.

But if you create a subclass called `Penguin`, which can’t fly, then `Penguin` **shouldn’t** be a subclass of `Bird` (because it would break the rule — the program might expect all birds to fly).

---

### Summary
LSP is about making sure **subclasses behave like their parent classes** without causing surprises or errors.
