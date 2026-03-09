# 13. Fluent API and annotations

## Fluent API (method chaining)

A **fluent API** lets you call multiple methods in a row on the same object, usually by having each method **return `this`** (the same object). So instead of:

```java
builder.setCheese(true);
builder.setSide("fries");
builder.build();
```

you write:

```java
builder.withCheese(true).withSide("fries").build();
```

Each method does its job (e.g. set a field) and returns `this`, so the next method runs on the same builder.

## Example

```java
public BurgerMealWithBuilder withCheese(boolean add) {
    this.cheese = add;
    return this;  // Return this so caller can chain: .withCheese(true).withSide("fries")
}

public BurgerMealWithBuilder withSide(String side) {
    this.side = side;
    return this;
}

public BurgerMeal build() {
    return new BurgerMeal(this);
}
```

## Annotations

**Annotations** attach extra information to code. The compiler or tools use them; they don’t run as normal code.

- **`@Override`** — marks a method that overrides a superclass or interface method. The compiler checks that a matching method actually exists in the parent/interface; if not, you get an error. Use it on every override so refactors don’t leave “orphan” methods.
- **`@SuppressWarnings("unused")`** — tells the compiler to suppress a specific warning (e.g. unused variable) for that element. Use sparingly.

## Example

```java
@Override
public void pay(double amount) {
    System.out.println("Paying " + amount);
}
```

```java
@SuppressWarnings("unused")
PaymentMethod pm = getSomePaymentMethod();  // We know we're not using pm here, suppress warning
```

## Where you see it in this repo

- **BuilderPattern/After:** `withCheese()`, `withSide()`, `withDrinks()` return `this` for fluent chaining; `.withCheese(true).withSide("fries").withDrinks(...).build()`.
- **Every interface implementation and override:** `@Override` on `pay()`, `send()`, `clone()`, `read()`, `write()`, etc.
- **LSP GoodCode Main:** `@SuppressWarnings("unused")` where a variable is intentionally unused for demonstration.
