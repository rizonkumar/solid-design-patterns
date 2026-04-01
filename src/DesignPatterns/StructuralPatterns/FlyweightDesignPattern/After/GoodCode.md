### The Flyweight Pattern Solution

The refactored code implements the **Flyweight Pattern**, a structural design pattern focused on minimizing memory usage by **sharing as much data as possible** between similar objects. This is achieved by separating an object's state into **Intrinsic** (shared) and **Extrinsic** (unique) data.

---

## 1. How Flyweight Pattern Solves the Issue

### TreeType Class (The Flyweight)
This class acts as the **flyweight object**. It stores data **common** to all trees of a given type—like name, color, and texture. Instead of duplicating this data for every single tree in the forest, we create **only one instance** per unique combination.

### Tree Class (The Context)
This class is now lightweight. It no longer carries heavy strings; instead, it only stores:
* **Extrinsic data**: $x, y$ coordinates (unique to each specific tree's location).
* **Reference to shared data**: A single pointer to a `TreeType` instance.

### TreeFactory Class (The Cache)
This is the **central factory** that ensures `TreeType` instances are reused. It acts as a gatekeeper: before creating a new type, it checks its internal map to see if that specific combination of name, color, and texture already exists.

---

## 2. Key Improvements

### Dramatic Memory Efficiency
Even with **1 million trees**, if they all share the same properties ("Oak", "Green", "Rough"), **only one `TreeType` object is created**. This reduces the memory footprint from hundreds of megabytes of redundant strings to a few kilobytes of shared data.

### Reduced Object Overhead
By moving the heavy data into a shared object, the individual `Tree` objects become extremely small. This allows the application to handle significantly larger datasets (billions of objects) that would have previously caused an `OutOfMemoryError`.

### Optimized Performance
* **Garbage Collection**: With fewer heavy objects to track, the Java Virtual Machine (JVM) spends less time on garbage collection, leading to smoother performance.
* **Rendering**: Modern graphics engines can use "instanced rendering" when they know multiple objects share the same mesh or texture data, which the Flyweight pattern facilitates.

---

## 3. Structural Breakdown

| Component | Responsibility |
| :--- | :--- |
| **Flyweight** (`TreeType`) | Contains the **Intrinsic State** (shared data). It must be immutable to prevent side effects. |
| **Context** (`Tree`) | Contains the **Extrinsic State** (unique data) and a reference to the Flyweight. |
| **Flyweight Factory** (`TreeFactory`) | Manages the creation and caching of Flyweight objects. |
| **Client** (`Forest`) | Computes or stores the extrinsic state and passes it to the Flyweight methods. |

---

## 4. Comparison of "Before" vs "After"

| Feature | Before (Heavyweight) | After (Flyweight) |
| :--- | :--- | :--- |
| **Memory for 1M Trees** | Very High (~100MB+) | Very Low (~15MB) |
| **Redundant Strings** | 1,000,000 copies of "Oak" | 1 copy of "Oak" |
| **Scalability** | Limited by RAM | High; limited only by coordinates |
| **State Management** | Tangled; all data in one class | Separated into shared vs. unique |

---

## 5. Summary of "Sharing"
The Flyweight Pattern is like a **Library**. Instead of every student buying their own copy of a textbook (expensive and space-consuming), the library keeps **one copy** that everyone shares. The only thing the student needs to keep track of is their own **bookmark** (the extrinsic state like $x, y$).
