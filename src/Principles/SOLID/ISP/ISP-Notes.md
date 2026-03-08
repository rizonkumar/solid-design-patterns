# Interface Segregation Principle (ISP)

The **Interface Segregation Principle (ISP)** states that:

> "No client should be forced to depend on methods it does not use."

In simpler terms, a class should **not be burdened with unnecessary methods** it doesn’t need.  
Instead of creating one large, general-purpose interface, it’s better to create **multiple smaller, specific interfaces** tailored to individual needs.

### Benefits

- Reduces unnecessary dependencies.
- Increases code maintainability and flexibility.
- Makes unit testing easier since each interface is focused on a single responsibility.