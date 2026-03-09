### The Prototype Pattern Solution

The refactored code implements the **Prototype Pattern** by using Java's `Cloneable` interface and a **Registry**. This approach shifts the responsibility of object creation from the `new` keyword to the objects themselves.

---

## 1. Key Improvements

### Performance via Memory Cloning

- **The Benefit:** Instead of re-running expensive constructor logic (DB fetches, template parsing), the system performs a memory-level copy.
- **The Result:** Creating the 1,000th email is as fast as creating the 2nd.

### Decoupling (Interface-Based Creation)

- **The Benefit:** The client (`Main`) no longer needs to know about the `WelcomeEmail` concrete class.
- **The Result:** You can add new email types (e.g., `PasswordResetEmail`) to the Registry without changing a single line of code in your business logic.

### Independent State (Anti-Singleton)

- **The Benefit:** Unlike a Singleton, which shares one instance, Prototype provides unique instances that share the same starting "blueprint."
- **The Result:** Modifying the content for "User A" has zero impact on "User B."

---

## 2. Structural Breakdown

| Component                                 | Responsibility                                                                                               |
| ----------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| **Prototype Interface** (`EmailTemplate`) | Declares the `clone()` method and the common contract for all templates.                                     |
| **Concrete Prototype** (`WelcomeEmail`)   | Implements the `clone()` method to return a copy of itself.                                                  |
| **Registry** (`EmailTemplateRegistry`)    | Acts as a "Warehouse" for master copies. It stores initialized prototypes and provides clones to the client. |
| **Client** (`Main`)                       | Requests a clone from the registry and configures the specific details needed.                               |

---

## 3. Comparison of "Before" vs "After"

| Feature              | Before (Manual `new`)                  | After (Prototype Pattern)                       |
| -------------------- | -------------------------------------- | ----------------------------------------------- |
| **Creation Speed**   | Slow (runs constructor every time).    | Fast (clones existing state).                   |
| **Dependency**       | Client depends on concrete classes.    | Client depends on the Interface and Registry.   |
| **Code Duplication** | High (setup logic repeated in client). | Low (setup logic hidden in Prototype/Registry). |
| **Scalability**      | Hard to manage many variations.        | Easy to manage via a Registry of templates.     |

---
