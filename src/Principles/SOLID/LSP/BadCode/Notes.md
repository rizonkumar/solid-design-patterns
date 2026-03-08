

## LSP Violation Example — "Bad Code"

### Code Overview
```java
package SOLID.LSP.BadCode;

public class File {
    public void read() {
        System.out.println("reading from file...");
    }

    public void write() {
        System.out.println("writing a file....");
    }
}

public class ReadOnlyFile extends File {
    // BAD: this class breaks the behavior expected from File
    public void write() {
        throw new UnsupportedOperationException("Can't write to a read-only file");
    }
}

public class Main {
    public static void main(String[] args) {
        File file = new ReadOnlyFile();
        file.read();  // works fine
        file.write(); // throws an exception — LSP violation
    }
}
````

---

### Why This Code is **Bad**

This code **violates the Liskov Substitution Principle (LSP)**.

LSP says that:

> *Subclasses should be replaceable for their parent classes without changing the expected behavior of the program.*

In this example:

* The parent class `File` allows both `read()` and `write()` operations.
* The subclass `ReadOnlyFile` **changes that behavior** by **throwing an exception** when `write()` is called.

If another piece of code expects a `File` object that can both **read** and **write**, substituting a `ReadOnlyFile` breaks that expectation — causing runtime errors.

---

### What’s Wrong Conceptually

* The `ReadOnlyFile` **does not behave like** a `File`.
* The method `write()` in the subclass **changes the meaning** of `write()` from the parent.
* Any code using `File` now has to add **extra checks** (e.g., “is this file read-only?”), which defeats the purpose of polymorphism.

---


