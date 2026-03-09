# 3. Abstract classes and inheritance

## Inheritance (`extends`)

**Inheritance** means one class is a **subtype** of another: the child **extends** the parent and gets its fields and methods. The child can add new ones or **override** existing methods.

- **Parent (superclass):** e.g. `ReadableFile`
- **Child (subclass):** e.g. `WritableFile extends ReadableFile`

In simple terms: the child “is a” kind of the parent (e.g. a `WritableFile` is a `ReadableFile`).

## Abstract class

An **abstract class** is a class that:
- Can have normal fields and methods with code (like a regular class).
- Can declare **abstract methods** (no body—only signature), meaning “subclasses must implement this.”
- Cannot be instantiated with `new AbstractClass()`; only concrete subclasses can be created.

Use it when you want **shared code** in one place but force subclasses to fill in specific behavior.

## `super()` in constructors

When a subclass has a constructor, it often needs to run the parent’s constructor first (to set up parent fields). You do that with `super(...)` as the **first line** of the child constructor.

## Example

```java
// Abstract = has shared code + one method subclasses must implement
public abstract class Card implements PaymentMethod {
    private String cardNo;
    private String userName;

    public Card(String cardNo, String name) {
        this.cardNo = cardNo;
        this.userName = name;
    }

    public String getCardNo() { return cardNo; }
    public String getUserName() { return userName; }
    // pay() is still from PaymentMethod — subclasses will implement it
}

// Concrete class: extends Card, must implement pay()
public class CreditCard extends Card {
    public CreditCard(String cardNo, String name) {
        super(cardNo, name);  // Call parent constructor first
    }

    @Override
    public void pay(double amount) {
        System.out.println("Credit card " + getCardNo() + " pays " + amount);
    }
}
```

- `Card` is abstract: shared state (cardNo, userName) and getters; `pay` comes from `PaymentMethod` and is implemented by subclasses.
- `CreditCard` **extends** `Card` and calls `super(cardNo, name)` so the parent’s fields are set.

## Where you see it in this repo

- **Basics/OOP:** `Card` is an abstract class; `CreditCard`, `DebitCard` extend it and call `super()`.
- **LSP GoodCode:** `ReadableFile` (base), `ReadOnlyFile extends ReadableFile`, `WritableFile extends ReadableFile implements Writable` — inheritance used so that read-only and writable files are both “readable” without breaking the contract.
