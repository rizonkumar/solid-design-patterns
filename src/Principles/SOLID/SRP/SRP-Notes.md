## SRP - The Single Responsibility Principle

The **Single Responsibility Principle (SRP)** states that a **class should have only one reason to change**,  
meaning it should have **only one responsibility** or **one job**.

---

### In Simpler Terms
Each class should focus on **doing one thing well**.  
If a class tries to handle too many tasks, it becomes harder to maintain, test, and update.

---

### Example
Suppose you have a `User` class.

- The `User` class should **only handle user-related logic** — such as storing the user’s name, email, and password.
- Tasks like **saving data to a database** should be handled by a **separate** class — for example, `UserRepository`.

This way, if the way you store data changes, you only update the `UserRepository`, not the `User` class.

---

### Summary
The **Single Responsibility Principle** helps keep your code **organized, maintainable, and flexible**  
by ensuring each class or module has **just one purpose**.
