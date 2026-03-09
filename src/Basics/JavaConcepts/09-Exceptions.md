# 9. Exception handling

## What are exceptions?

**Exceptions** are Java’s way of signaling that something went wrong (invalid input, missing resource, unsupported operation, etc.). If not handled, they can stop the program. You **handle** them with `try/catch` or **signal** them with `throw`.

## Key ideas

- **`throw new SomeException("message")`** — create and throw an exception. Control leaves the current method until something catches it.
- **`try { ... } catch (ExceptionType e) { ... }`** — run the code in `try`; if it throws that exception type, run the `catch` block (e.g. log, convert to another exception, return default).
- **Checked vs unchecked:** Some exceptions (e.g. `CloneNotSupportedException`) are “checked”: the compiler forces you to either handle them or declare `throws` on the method. Runtime exceptions (e.g. `IllegalArgumentException`, `UnsupportedOperationException`) are unchecked.

## Example: handling (try/catch)

```java
@Override
public WelcomeEmail clone() {
    try {
        return (WelcomeEmail) super.clone();
    } catch (CloneNotSupportedException e) {
        throw new RuntimeException("Clone failed", e);
    }
}
```

- `super.clone()` can throw `CloneNotSupportedException` (checked). We catch it and wrap it in a `RuntimeException` so callers don’t have to declare `throws`.

## Example: throwing (signaling error)

```java
public static Logistics getLogistics(String mode) {
    if ("road".equals(mode)) return new Road();
    if ("air".equals(mode)) return new Air();
    throw new IllegalArgumentException("Unknown logistics mode: " + mode);
}
```

```java
// LSP/ISP bad examples: unsupported operation
@Override
public void write(String content) {
    throw new UnsupportedOperationException("Read-only file cannot be written");
}
```

- First: invalid argument → throw `IllegalArgumentException`.
- Second: operation not allowed for this type → throw `UnsupportedOperationException`.

## Where you see it in this repo

- **Prototype:** `clone()` in try-catch for `CloneNotSupportedException`.
- **Factory / Abstract Factory:** `IllegalArgumentException` for unknown mode or unsupported region.
- **OCP BadCode:** `IllegalArgumentException` for unsupported payment method.
- **LSP/ISP BadCode:** `UnsupportedOperationException` when a method isn’t supported (e.g. write on read-only file, scan on simple printer).
