---

# Creational Design Patterns: Master Comparison

Creational patterns deal with **object creation mechanisms**, trying to create objects in a manner suitable to the situation. The basic form of object creation (the `new` operator) could result in design problems or added complexity to the design. Creational design patterns solve this problem by somehow **controlling this object creation**.

## 1. Quick Comparison Table


| Pattern              | Primary Focus    | Key Mechanism                                        | Best For...                                                        |
| -------------------- | ---------------- | ---------------------------------------------------- | ------------------------------------------------------------------ |
| **Singleton**        | **Identity**     | Controlled access to a single instance.              | Shared resources (Loggers, DB pools).                              |
| **Factory Method**   | **Choice**       | Delegating creation of a single object to a factory. | When you have one interface with many implementations (Logistics). |
| **Abstract Factory** | **Families**     | Creating groups of related objects together.         | Regional requirements (Payment + Invoice).                         |
| **Builder**          | **Step-by-Step** | Constructing a complex object part-by-part.          | Objects with many optional fields (BurgerMeal).                    |
| **Prototype**        | **Copying**      | Cloning an existing pre-configured object.           | Heavy initialization cost (Email Templates).                       |


---

## 2. When to Use vs. When to Avoid

### **Singleton Pattern**

- **When to Use:** When exactly one instance of a class is needed to coordinate actions across the system (e.g., a central error logger).
- **When to Avoid:** For general data storage or when you need independent instances for different users. Overusing it can make unit testing difficult.

### **Factory Method Pattern**

- **When to Use:** When a class cannot anticipate the type of objects it must create and wants its subclasses to specify them.
- **When to Avoid:** When the number of products is small and unlikely to change, as it adds unnecessary abstraction layers.

### **Abstract Factory Pattern**

- **When to Use:** When your system needs to be independent of how its products are created and needs to enforce a "family" of related products working together.
- **When to Avoid:** When you only have one family of products, as it requires creating many interfaces and factory classes.

### **Builder Pattern**

- **When to Use:** When you need to create an object with a lot of optional parameters or when the construction process must allow different representations.
- **When to Avoid:** When the object is simple or has only a few mandatory fields; a standard constructor is much faster to write.

### **Prototype Pattern**

- **When to Use:** When the cost of creating a brand-new instance is high (e.g., database hits or complex parsing) or when you want to keep the number of classes in a system to a minimum.
- **When to Avoid:** When objects are simple to create or when the object's internal state involves complex circular references that are hard to deep-clone.

---

## 3. The "Rule of Thumb" for Decision Making

1. **Is it a single global instance?** → **Singleton**.
2. **Is the creation process "heavy" or repetitive?** → **Prototype**.
3. **Does the object have 5+ optional configuration parts?** → **Builder**.
4. **Are you choosing between implementations of one interface?** → **Factory Method**.
5. **Are you choosing a whole suite of related tools based on a region/theme?** → **Abstract Factory**.

