# Structural Design Patterns: Master Comparison

Structural design patterns deal with **how classes and objects are composed** to form larger structures. They ensure that if one part of a system changes, the entire structure doesn’t need to do the same. These patterns focus on **simplifying relationships** between entities, enhancing flexibility, and optimizing resource usage.

---

## 1. Quick Comparison Table

| Pattern | Primary Focus | Key Mechanism | Best For... |
| :--- | :--- | :--- | :--- |
| **Adapter** | **Compatibility** | Wrapping an object to provide a different interface. | Integrating 3rd party legacy code or incompatible APIs. |
| **Bridge** | **Independence** | Decoupling abstraction from its implementation. | Cross-platform apps (e.g., UI for Windows vs. MacOS). |
| **Composite** | **Hierarchy** | Treating individual objects and collections uniformly. | Tree structures like File Systems or UI Widget trees. |
| **Decorator** | **Extension** | Adding responsibilities dynamically without inheritance. | Optional features like scrollbars on windows or pizza toppings. |
| **Facade** | **Simplicity** | Providing a unified, simple entry point to a complex system. | Simplifying interaction with a messy library or microservices. |
| **Proxy** | **Control** | Acting as a placeholder to control access to an object. | Caching, logging, or lazy loading expensive resources. |
| **Flyweight** | **Memory** | Sharing common state across many similar objects. | Managing millions of small objects (e.g., trees in a game). |

---

## 2. When to Use vs. When to Avoid

### **Adapter Pattern**
- **When to Use:** When you want to use an existing class, but its interface does not match the one you need.
- **When to Avoid:** When you have control over the source code; it's better to refactor the original class to match the interface if possible.

### **Bridge Pattern**
- **When to Use:** When you want to avoid a permanent binding between an abstraction and its implementation (e.g., different "Players" and different "Resolutions").
- **When to Avoid:** When you have only one dimension of change; it adds unnecessary complexity and boilerplate code.

### **Composite Pattern**
- **When to Use:** When you need to represent part-whole hierarchies and want clients to ignore the difference between compositions and individual objects.
- **When to Avoid:** When the behavior of a leaf and a container is too different, making the shared interface awkward or "leaky."

### **Decorator Pattern**
- **When to Use:** When you need to add behaviors to objects at runtime without breaking other objects of the same class.
- **When to Avoid:** When you have a long chain of decorators, as it makes debugging and identifying which layer caused an error very difficult.

### **Facade Pattern**
- **When to Use:** When you want to provide a simple interface to a complex subsystem or layer your system into distinct levels of responsibility.
- **When to Avoid:** When you need to provide full access to the subsystem’s features; the facade can become a "God Object" if it tries to do too much.

### **Proxy Pattern**
- **When to Use:** When you need a more versatile or sophisticated reference to an object than a simple pointer (e.g., Caching Proxy, Virtual Proxy, Protection Proxy).
- **When to Avoid:** When the resource is lightweight and access is frequent; the extra layer of indirection can introduce unnecessary latency.

### **Flyweight Pattern**
- **When to Use:** When an application uses a vast number of objects and most of their state can be made "intrinsic" (shared).
- **When to Avoid:** When memory is not a constraint or when objects have almost no shared state; the split between intrinsic and extrinsic state adds logic overhead.

---

## 3. The "Rule of Thumb" for Decision Making

1. **Are the interfaces incompatible?** → **Adapter**.
2. **Do you have a "Class Explosion" due to two independent dimensions?** → **Bridge**.
3. **Do you have a "Part-Whole" tree structure?** → **Composite**.
4. **Need to add features like "Onion Skins" at runtime?** → **Decorator**.
5. **Need a "Help Desk" for a messy, multi-service system?** → **Facade**.
6. **Need a "Gatekeeper" to control or cache access?** → **Proxy**.
7. **Are you running out of RAM because of too many objects?** → **Flyweight**.
