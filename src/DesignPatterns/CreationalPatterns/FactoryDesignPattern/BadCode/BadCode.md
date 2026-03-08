# Problem Analysis: Concrete Dependency & Hardcoded Instantiation

## 1. Tight Coupling

The `LogisticsService` is directly dependent on the concrete classes `Air` and `Road`.

- **The Issue:** The high-level service "knows" exactly how to create the low-level transport objects.
- **The Impact:** If you want to rename a class, change a constructor, or delete a transport mode, you are forced to modify the `LogisticsService`.

## 2. Violation of the Open/Closed Principle (OCP)

Your code should be open for extension but closed for modification.

- **The Issue:** Every time a new transport mode is added (e.g., `Sea`, `Rail`, `Drone`), you must open the `LogisticsService.java` file and add another `else-if` block.
- **The Impact:** You are constantly risking the stability of your core service logic just to add a new delivery option.

## 3. "God Method" Logic

The `send(String mode)` method is taking on two separate responsibilities (violating the **Single Responsibility Principle**).

- **Responsibility 1:** Deciding _which_ object to create (Creation Logic).
- **Responsibility 2:** Executing the business logic (`logistics.send()`).
- **The Impact:** As the application scales, this method becomes a massive, unmanageable block of conditional logic that is difficult to unit test.

## 4. Brittle String Comparisons

The logic relies on raw string comparisons like `mode.equals("Air")`.

- **The Issue:** A small typo (e.g., `"air"` vs `"Air"`) or a change in naming convention will cause the system to fail at runtime.
- **The Impact:** There is no compile-time safety to ensure that the requested mode actually exists or is handled correctly.

## Summary

The "New" keyword is a powerful tool, but when it appears inside your business logic, it creates a "Concrete Dependency." The **Factory Method Pattern** solves this by moving the `new` keyword out of the service and into a dedicated creator class.
