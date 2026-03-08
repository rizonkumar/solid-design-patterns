### The Singleton Solution

The refactored code ensures that **only one instance** of `JudgeAnalytics` exists in the application. All callers share the same object, so run and submit counts are truly global.

---

## 1. Key Improvements

### Single Instance Guarantee

- **The Benefit:** No matter how many times `JudgeAnalytics.getInstance()` is called, the same instance is returned. There is exactly one place holding run/submit state.
- **The Safety:** You cannot accidentally create a second analytics object; the constructor is private.

### Global State in One Place

- **The Benefit:** Dashboards, reports, and rate-limit logic can rely on a single source of truth. "Total runs" and "total submits" are well-defined.
- **The Impact:** Any part of the system that calls `getInstance()` sees the same counts.

### Lazy and Thread-Safe Initialization

- **The Benefit:** The instance is created only when first needed (lazy). The static-holder idiom ensures thread-safe creation without explicit locking.
- **The Result:** Safe to use from multiple threads; no double initialization.

---

## 2. Structural Breakdown

| Component              | Responsibility                                                                 |
| ---------------------- | ------------------------------------------------------------------------------ |
| **Singleton**          | Class that allows only one instance of itself (private constructor + getInstance). |
| **Static holder**      | Nested class used to hold the single instance; loaded by JVM when first accessed.  |
| **Client** (e.g. Main) | Uses `Singleton.getInstance()` instead of `new Singleton()`.                   |

---

## 3. Comparison of "Before" vs "After"

| Feature           | Before (Multiple Instances)           | After (Singleton)                          |
| ----------------- | ------------------------------------- | ------------------------------------------ |
| **Instantiation** | `new JudgeAnalytics()` anywhere.      | Only via `JudgeAnalytics.getInstance()`.   |
| **Number of instances** | Unbounded; each caller can have its own. | Exactly one.                               |
| **Global counts** | Not possible; state is fragmented.    | Single object holds all run/submit data.   |
| **Testing**       | Hard to assert on "the" analytics.    | One instance to inject or mock.            |

---
