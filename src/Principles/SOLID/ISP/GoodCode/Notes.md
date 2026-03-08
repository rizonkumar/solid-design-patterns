# How the Good Code Follows the Interface Segregation Principle (ISP)

To fix the earlier violation, the `Machine` interface is divided into smaller, more specific interfaces:

- `Printer` – handles printing functionality.
- `Scanner` – handles scanning functionality.
- `Copier` – handles copying functionality.

### How This Design Follows ISP

1. Each class now depends only on the methods it actually uses.
2. `SimplerPrinter` implements only the `Printer` interface since it only needs to print.
3. `MultiPurposeMachine` implements all three interfaces because it supports all functionalities.

### Benefits of This Approach

- Classes are not forced to implement unnecessary methods.
- Each interface is small and focused, improving flexibility and readability.
- Reduces the chance of runtime errors like `UnsupportedOperationException`.
- Makes the system easier to extend and maintain.
