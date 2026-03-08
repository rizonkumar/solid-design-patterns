# ISP Violation in Bad Code Example

In the given code, the `Machine` interface has three methods:

- `print(Document doc)`
- `scan(Document doc)`
- `copy(Document doc)`

### Problem

Not all classes using this interface need all three methods.

For example:

- `SimplePrinter` only needs to **print**, but it is **forced** to implement `scan()` and `copy()`, which it doesn’t
  support.
- To handle this, it throws `UnsupportedOperationException`, leading to poor design and potential runtime errors.

### Why It Violates ISP

The `Machine` interface is **too large** and forces classes to depend on methods they don’t need.  
This makes the code **less flexible** and **harder to maintain**.

A better approach is to create **separate interfaces** like `Printer`, `Scanner`, and `Copier`, allowing classes to
implement only what they require.
