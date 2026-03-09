# 8. Generics

## What are generics?

**Generics** let you write classes and methods that work with a **type you specify later**, so the compiler can check types and you avoid casting. You see them most in **collections**: `List<String>`, `Map<String, EmailTemplate>`, `Stack<EditorMemento>`.

In simple terms:
- `List` = “a list of something.”
- `List<String>` = “a list of **String** only.” The compiler knows every element is a String, and won’t let you add an Integer.

## Why use them?

- **Type safety:** No accidental wrong types in the list or map.
- **No casting:** You don’t need `(String) list.get(0)`; the compiler already knows it’s a String.
- **Clear intent:** `Map<String, EmailTemplate>` tells you “keys are strings, values are email templates.”

## Example

```java
// List of strings only
List<String> drinks = new ArrayList<>();
drinks.add("Cola");
drinks.add("Water");
String first = drinks.get(0);  // No cast needed

// Map: key = String, value = EmailTemplate
Map<String, EmailTemplate> templates = new HashMap<>();
templates.put("welcome", new WelcomeEmail());
EmailTemplate t = templates.get("welcome");  // Type is EmailTemplate

// Stack of mementos
Stack<EditorMemento> history = new Stack<>();
history.push(new EditorMemento("content"));
EditorMemento m = history.pop();
```

## Syntax in a class

```java
public class Registry<T> {
    private final Map<String, T> map = new HashMap<>();

    public void put(String key, T value) {
        map.put(key, value);
    }

    public T get(String key) {
        return map.get(key);
    }
}
// Use: Registry<EmailTemplate> reg = new Registry<>();
```

- `T` is the “type parameter.” When you use `Registry<EmailTemplate>`, `T` becomes `EmailTemplate` everywhere in that instance.

## Where you see it in this repo

- **Builder:** `List<String> drinks` in burger meal; builder uses `List<String>`.
- **Prototype:** `Map<String, EmailTemplate> templates` in `EmailTemplateRegistry`.
- **Observer:** `List<Observer> observerList` / `observers`.
- **Memento:** `Stack<EditorMemento> history` in `Caretaker`.
