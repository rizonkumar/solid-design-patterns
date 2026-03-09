# 10. Cloneable and `clone()`

## What is Cloneable?

**`Cloneable`** is a **marker interface** in Java (no methods). If a class **implements Cloneable**, it is allowed to use the default cloning behavior from `Object.clone()`. If it doesn’t implement it, calling `super.clone()` can throw `CloneNotSupportedException`.

**`clone()`** in `Object` does a **shallow copy**: it creates a new object and copies field values. For fields that are references (e.g. other objects, lists), the new object’s fields point to the **same** objects as the original. For a full independent copy of nested structures, you’d implement a **deep copy** yourself inside `clone()`.

## Why use it?

When creating a new instance is **expensive** (e.g. heavy setup, DB calls) but you want a **copy** with small changes, cloning can be cheaper than `new` + copying fields by hand. The **Prototype** pattern uses this: keep a “master” object and clone it whenever you need a copy.

## Example

```java
interface EmailTemplate extends Cloneable {
    EmailTemplate clone();
    void setContent(String content);
}

class WelcomeEmail implements EmailTemplate {
    private String subject;
    private String content;

    public WelcomeEmail() {
        this.subject = "Welcome";
        this.content = "Hi there!";
    }

    @Override
    public WelcomeEmail clone() {
        try {
            // Shallow copy: new WelcomeEmail with same subject/content values
            return (WelcomeEmail) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone failed", e);
        }
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }
}
```

Usage:

```java
EmailTemplate master = new WelcomeEmail();
EmailTemplate copy = master.clone();  // Copy, no heavy constructor
copy.setContent("Hi Alice!");         // Change only the copy
// master unchanged; copy is independent (for primitive/String fields)
```

## Important

- **Always return a clone from a registry**, not the original, so the “master” stays unchanged.
- For objects with **nested mutable state** (e.g. lists, other objects), override `clone()` and copy those too (deep copy) so the clone is fully independent.

## Where you see it in this repo

- **PrototypeDesignPattern/After:** `EmailTemplate extends Cloneable`, `WelcomeEmail.clone()` using `super.clone()`, and `EmailTemplateRegistry.getTemplate(type)` returns `templates.get(type).clone()` so clients get copies, not the stored prototype.
