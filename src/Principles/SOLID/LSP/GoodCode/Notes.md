
## LSP - Good Code Example


---

### Code Overview

```java 
package SOLID.LSP.GoodCode;

public interface Readable {
    void read();
}

public interface Writable {
    void write();
}

public class ReadableFile implements Readable {
    @Override
    public void read() {
        System.out.println("Reading from a file");
    }
}

public class ReadOnlyFile extends ReadableFile {
    // Inherits only reading behavior
}

public class WritableFile extends ReadableFile implements Writable {
    @Override
    public void write() {
        System.out.println("Writing to a file...");
    }
}

public class Main {
    public static void readAnyFile(ReadableFile file) {
        file.read();
    }

    public static void main(String[] args) {
        ReadableFile readableFile = new ReadOnlyFile();
        readableFile.read();

        WritableFile writableFile = new WritableFile();
        writableFile.read();
        writableFile.write();

        readAnyFile(readableFile);
        readAnyFile(writableFile);
    }
}
```

---

### Why This Code is **Good**

This design **respects LSP** because:

1. **All subclasses behave like their parent class** (`ReadableFile`):

    * `ReadOnlyFile` can be used anywhere a `ReadableFile` is expected.
    * `WritableFile` adds new functionality (`write()`) **without breaking** the behavior of `ReadableFile`.

2. **No method throws unexpected exceptions** or removes capabilities promised by the parent class.

3. **Polymorphism works safely** — you can substitute a `ReadableFile` reference with either `ReadOnlyFile` or `WritableFile` without breaking the program.

---

### Class Hierarchy Diagram

```
           ┌────────────────────┐
           │     Readable       │
           │     (Interface)    │
           │        [R]         │
           └─────────┬──────────┘
                     │
           ┌─────────▼──────────┐
           │   ReadableFile     │
           │   implements R     │
           │        [R]         │
           └─────────┬──────────┘
             ┌───────┴────────┐
             │                │
 ┌───────────▼────────┐   ┌───▼───────────────┐
 │   ReadOnlyFile      │   │   WritableFile    │
 │   extends R-File    │   │   extends R-File  │
 │        [R]          │   │ implements W [R+W]│
 └─────────────────────┘   └───────────────────┘
```

* `[R]` = **Readable** capability
* `[R+W]` = **Readable + Writable** capability

---

### Benefits of This Design

| Concept            | Description                                                                                                      |
| ------------------ | ---------------------------------------------------------------------------------------------------------------- |
| **LSP Compliance** | Subclasses (`ReadOnlyFile`, `WritableFile`) can replace their parent (`ReadableFile`) without breaking behavior. |
| **Extensibility**  | You can easily add more file types (e.g., `EncryptedFile`) without changing existing code.                       |
| **Safety**         | No runtime exceptions due to unsupported operations.                                                             |
| **Clarity**        | Each class clearly expresses its capabilities — readable, writable, or both.                                     |

---

### Summary

By separating **read** and **write** responsibilities into clear interfaces (`Readable`, `Writable`) and ensuring subclasses extend functionality **without restricting** parent behavior,
this code follows the **Liskov Substitution Principle** perfectly.

**Every subclass can stand in for its parent without surprises or errors.**


