---
## Table of Contents

1. [Class and Object](#1-class-and-object)
2. [Access Modifiers](#2-access-modifiers)
3. [Constructors](#3-constructors)
4. [this and super Keywords](#4-this-and-super-keywords)
5. [Static vs Instance Members](#5-static-vs-instance-members)
6. [Inheritance](#6-inheritance)
7. [Polymorphism](#7-polymorphism)
8. [Encapsulation](#8-encapsulation)
9. [Abstraction](#9-abstraction)
10. [Abstract Classes vs Interfaces](#10-abstract-classes-vs-interfaces)
11. [Multiple Inheritance with Interfaces](#11-multiple-inheritance-with-interfaces)
12. [Inner Classes](#12-inner-classes)
13. [Final Keyword](#13-final-keyword)
14. [Object Class and Methods](#14-object-class-and-methods)
15. [Exception Handling in OOP](#15-exception-handling-in-oop)
16. [Best Practices](#16-best-practices)
---

## 1. Class and Object

### Class

A **class** is a blueprint or template for creating objects. It defines:

- **Fields (variables)**: Store data/state
- **Methods (functions)**: Define behavior
- **Constructors**: Initialize objects

### Object

An **object** is an instance of a class with its own **state** and **behavior**.

```java
class Car {
    String brand;
    int year;
}
```

---

## 2. Access Modifiers

Access modifiers control the visibility and accessibility of classes, methods, and variables.

| Modifier      | Class | Package | Subclass | World |
| ------------- | ----- | ------- | -------- | ----- |
| **public**    | ✅    | ✅      | ✅       | ✅    |
| **protected** | ✅    | ✅      | ✅       | ❌    |
| **default**   | ✅    | ✅      | ❌       | ❌    |
| **private**   | ✅    | ❌      | ❌       | ❌    |

```java
class Example {
    public int pub = 1;      // Accessible everywhere
    protected int prot = 2;  // Accessible in subclass
    int def = 3;             // Accessible in package
    private int priv = 4;    // Accessible only in this class
}
```

---

## 3. Constructors

Constructors initialize objects when they are created. They have the same name as the class and **no return type**.

### Types of Constructors:

1. **Default Constructor** (no parameters)
2. **Parameterized Constructor** (with parameters)
3. **Copy Constructor** (creates copy of another object)

```java
class Employee {
    String name;

    Employee() {}  // Default constructor

    Employee(String name) {  // Parameterized constructor
        this.name = name;
    }
}
```

---

## 4. `this` and `super` Keywords

### `this` Keyword

- Refers to the **current object** instance
- Used to distinguish between instance variables and parameters
- Can be used to call other constructors in the same class

### `super` Keyword

- Refers to the **parent class** instance
- Used to call parent class constructors or methods
- Access parent class members when overridden

```java
class Parent {
    String name = "Parent";
}

class Child extends Parent {
    String name = "Child";

    void show() {
        System.out.println(this.name);  // Child's name
        System.out.println(super.name); // Parent's name
    }
}
```

---

## 5. Static vs Instance Members

### Static Members

- Belong to the **class**, not instances
- Shared among all instances
- Can be accessed without creating objects
- Memory allocated once when class is loaded

### Instance Members

- Belong to **individual objects**
- Each object has its own copy
- Require object creation to access

```java
class Example {
    static int count = 0;  // Shared by all objects
    int id;                 // Unique to each object

    Example() {
        count++;       // Changes for all objects
        id = count;    // Unique per object
    }
}
```

---

## 6. Inheritance

Inheritance allows one class to **inherit fields and methods** from another class. It promotes code reusability and establishes relationships between classes.

### Types of Inheritance:

1. **Single Inheritance**: One class extends another
2. **Multilevel Inheritance**: Class extends another which extends another
3. **Hierarchical Inheritance**: Multiple classes extend one base class

```java
class Animal {
    void eat() {}
}

class Dog extends Animal {  // Single inheritance
    void bark() {}
}

class Puppy extends Dog {   // Multilevel inheritance
    void play() {}
}
```

---

## 7. Polymorphism

**Polymorphism** means "many forms" — allows one interface to be used for different data types.

### Types of Polymorphism:

#### 1. Compile-time Polymorphism (Method Overloading)

- Same method name, different parameters
- Resolved at compile time
- Also called **static polymorphism**

#### 2. Runtime Polymorphism (Method Overriding)

- Same method signature in parent and child classes
- Resolved at runtime based on actual object type
- Also called **dynamic polymorphism**

```java
// Method Overloading
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}

// Method Overriding
class Animal {
    void sound() {}
}

class Dog extends Animal {
    @Override
    void sound() {}
}
```

---

## 8. Encapsulation

Encapsulation means **binding data and methods** together and keeping them **safe from outside interference** using **private** variables and **public getters/setters**.

### Benefits:

- **Data Hiding**: Internal state is protected
- **Controlled Access**: Data can only be accessed through defined methods
- **Validation**: Can add validation logic in setters
- **Flexibility**: Internal implementation can change without affecting external code

```java
class Account {
    private double balance;  // Private field

    public double getBalance() {  // Public getter
        return balance;
    }
}
```

---

## 9. Abstraction

Abstraction hides complex implementation and shows only the **necessary details** using **abstract classes** or **interfaces**.

### When to use Abstraction:

- When you want to provide a common interface for different implementations
- When you want to hide implementation details
- When you have some common functionality but some behavior must be implemented differently

```java
abstract class Shape {
    abstract double getArea();  // Abstract method
}

class Circle extends Shape {
    double radius;

    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }
}
```

---

## 10. Abstract Classes vs Interfaces

### Abstract Classes

- Can have both abstract and concrete methods
- Can have instance variables
- Can have constructors
- Support single inheritance
- Used when classes share common behavior

### Interfaces

- Can only have abstract methods (Java 8+ allows default and static methods)
- Cannot have instance variables (only constants)
- Cannot have constructors
- Support multiple inheritance
- Used to define contracts

```java
// Abstract class - can have concrete methods
abstract class Appliance {
    abstract void turnOn();  // Abstract method
    void display() {}        // Concrete method
}

// Interface - only abstract methods (Java 8+ allows defaults)
interface RemoteControl {
    void changeChannel(int channel);
    default void info() {}   // Default method
}

class TV extends Appliance implements RemoteControl {
    @Override
    void turnOn() {}

    @Override
    public void changeChannel(int channel) {}
}
```

---

## 11. Multiple Inheritance with Interfaces

Java doesn't support multiple inheritance with classes but supports it with interfaces. This allows a class to inherit behavior from multiple sources.

```java
// Multiple inheritance with interfaces
interface Swimmer {
    void swim();
}

interface Flyer {
    void fly();
}

class Duck implements Swimmer, Flyer {
    @Override
    public void swim() {}

    @Override
    public void fly() {}
}
```

---

## 12. Inner Classes

Inner classes are classes defined within another class. They provide better encapsulation and can access outer class members.

### Types of Inner Classes:

1. **Member Inner Class** (non-static)
2. **Static Nested Class**
3. **Local Inner Class** (inside methods)
4. **Anonymous Inner Class**

```java
class Outer {
    int x = 10;

    // Member inner class
    class Inner {
        void show() {
            System.out.println("Outer x: " + x);
        }
    }

    // Static nested class
    static class Nested {
        void show() {
            System.out.println("Static nested class");
        }
    }
}
```

---

## 13. Final Keyword

The `final` keyword can be applied to:

- **Variables**: Cannot be changed after initialization
- **Methods**: Cannot be overridden
- **Classes**: Cannot be extended

```java
// Final class - cannot be extended
final class Constants {
    public static final double PI = 3.14;
}

// Final variables and methods
class Example {
    private final int id;     // Final field
    private String name;

    Example(int id) {
        this.id = id;         // Initialize final field
    }

    public final int getId() { // Final method - cannot override
        return id;
    }
}
```

---

## 14. Object Class and Methods

All Java classes inherit from the `Object` class. It provides fundamental methods that all objects have.

```java
class Person {
    String name;

    // Override Object methods
    @Override
    public String toString() {
        return "Person{name='" + name + "'}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Person) {
            return name.equals(((Person) obj).name);
        }
        return false;
    }
}
```

---

## 15. Exception Handling in OOP

Exception handling in OOP promotes robust and maintainable code by separating error-handling logic from normal business logic.

```java
// Custom exception
class InvalidAgeException extends Exception {
    InvalidAgeException(String message) {
        super(message);
    }
}

class Person {
    private int age;

    void setAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Invalid age: " + age);
        }
        this.age = age;
    }
}
```

---

## 16. Best Practices

### 1. **SOLID Principles**

- **S**ingle Responsibility: A class should have only one reason to change
- **O**pen-Closed: Open for extension, closed for modification
- **L**iskov Substitution: Subtypes should be substitutable for their base types
- **I**nterface Segregation: Clients shouldn't depend on methods they don't use
- **D**ependency Inversion: Depend on abstractions, not concretions

### 2. **Class Design Best Practices**

- Use meaningful names for classes, methods, and variables
- Keep classes focused on a single responsibility
- Prefer composition over inheritance when possible
- Use appropriate access modifiers
- Document your code with meaningful comments

### 3. **Exception Handling Best Practices**

- Use checked exceptions for recoverable errors
- Use unchecked exceptions for programming errors
- Provide meaningful error messages
- Don't suppress exceptions without good reason
- Use try-with-resources for automatic resource management

### 4. **Performance Considerations**

- Initialize collections with appropriate initial capacity
- Use StringBuilder for string concatenation in loops
- Cache frequently used objects
- Avoid unnecessary object creation

```java
class UserService {
    private final UserRepository repository;
    private final EmailService emailService;

    // Constructor injection (Dependency Inversion)
    UserService(UserRepository repository, EmailService emailService) {
          this.repository = repository;
          this.emailService = emailService;
      }

      // Single Responsibility: Only handles user operations
      public Optional<User> findUserById(String id) {
          try {
              return repository.findById(id);
          } catch (Exception e) {
              System.err.println("Error finding user: " + e.getMessage());
              return Optional.empty();
          }
      }

      public boolean createUser(User user) {
          // Validation
          if (user == null || user.getEmail() == null) {
              return false;
          }

          try {
              repository.save(user);
              emailService.sendWelcomeEmail(user.getEmail());
              return true;
          } catch (Exception e) {
              System.err.println("Error creating user: " + e.getMessage());
              return false;
          }
      }

  }

// Interface for repository (Dependency Inversion)
interface UserRepository {
Optional<User> findById(String id);
void save(User user);
}

// Interface for email service (Interface Segregation)
interface EmailService {
void sendWelcomeEmail(String email);
void sendPasswordResetEmail(String email);
}

// Concrete implementation
class DatabaseUserRepository implements UserRepository {
@Override
public Optional<User> findById(String id) {
// Database implementation
return Optional.empty(); // Simplified
}

    @Override
    public void save(User user) {
        // Database save implementation
        System.out.println("User saved to database: " + user.getName());
    }

}

class SmtpEmailService implements EmailService {
@Override
public void sendWelcomeEmail(String email) {
System.out.println("Welcome email sent to: " + email);
}

    @Override
    public void sendPasswordResetEmail(String email) {
        System.out.println("Password reset email sent to: " + email);
    }

}

// Value object (Immutable)
final class User {
private final String id;
private final String name;
private final String email;

    User(String id, String name, String email) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.email = Objects.requireNonNull(email, "Email cannot be null");
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    // Proper equals and hashCode for value objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{id='" + id + "', name='" + name + "', email='" + email + "'}";
    }

}

public class BestPracticesDemo {
public static void main(String[] args) {
// Dependency injection
UserRepository repository = new DatabaseUserRepository();
EmailService emailService = new SmtpEmailService();
UserService userService = new UserService(repository, emailService);

        // Create user with validation
        User user = new User("123", "John Doe", "john@example.com");

        // Use service
        boolean created = userService.createUser(user);
        System.out.println("User created: " + created);

        // Find user
        Optional<User> foundUser = userService.findUserById("123");
        foundUser.ifPresent(u -> System.out.println("Found user: " + u));

        // Demonstrate immutability
        System.out.println("Original user: " + user);
        // user.name = "Jane Doe"; // ❌ Cannot modify - final fields
    }

}

```

---

## Summary of Core OOP Concepts

| Concept           | Purpose               | Key Benefits                   |
| ----------------- | --------------------- | ------------------------------ |
| **Class**         | Blueprint for objects | Code organization, reusability |
| **Object**        | Instance of a class   | Represents real-world entities |
| **Encapsulation** | Data hiding           | Security, maintainability      |
| **Inheritance**   | Code reuse            | Hierarchy, polymorphism        |
| **Polymorphism**  | Multiple forms        | Flexibility, extensibility     |
| **Abstraction**   | Hide complexity       | Simplicity, modularity         |

---
