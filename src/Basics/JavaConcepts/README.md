# Java Concepts Used in Design Patterns & SOLID

Short explanations and examples for every Java concept you’ll see in **DesignPatterns** and **Principles/SOLID**. Each concept is in simpler terms with a small example and pointers to where it’s used in this repo.

---

## Index

| # | Concept | One-line summary | Where it's used |
|---|---------|------------------|------------------|
| 1 | [Interfaces](01-Interfaces.md) | A contract: “any implementation must have these methods.” | Factory, Abstract Factory, Prototype, Observer; OCP, DIP, ISP, LSP |
| 2 | [Implementing interfaces (`implements`)](02-Implements.md) | A class promises to provide all methods declared in an interface. | All pattern and SOLID examples that use interfaces |
| 3 | [Abstract classes & inheritance](03-AbstractClassesAndInheritance.md) | Base class with shared code; subclasses extend it and use `super()`. | Basics/OOP (`Card`); LSP (file hierarchy) |
| 4 | [Encapsulation](04-Encapsulation.md) | Hide data in `private` fields; expose only via getters/setters. | Builder, Singleton, Observer, Memento; SRP, DIP |
| 5 | [Constructors](05-Constructors.md) | Code that runs when you create an object; can be parameterized or private. | Every class; Singleton (private), Builder (private), DIP (injection) |
| 6 | [`static` and `final`](06-StaticAndFinal.md) | `static`: one per class. `final`: cannot be reassigned. | `main`, factories, Singleton holder, constants, immutable fields |
| 7 | [Method overriding & `@Override`](07-OverridingAndPolymorphism.md) | Subclass/implementation defines its own version of a method; polymorphism = one type, many behaviors. | All interface implementations; LSP (substitutability) |
| 8 | [Generics](08-Generics.md) | `List<String>`, `Map<K,V>`: type-safe collections. | Builder (`List<String>`), Prototype (`Map<String, EmailTemplate>`), Observer, Memento |
| 9 | [Exception handling](09-Exceptions.md) | `try/catch` to handle errors; `throw` to signal them. | Prototype (`clone`), Factory/AbstractFactory (invalid input), LSP/ISP (unsupported ops) |
| 10 | [Cloneable & `clone()`](10-Cloneable.md) | Java’s way to copy an object instead of creating a new one from scratch. | Prototype pattern (After) |
| 11 | [Inner & static nested classes](11-InnerAndNestedClasses.md) | Class defined inside another; static nested = one per outer class, no outer instance needed. | Singleton (holder), Builder (builder as static nested class) |
| 12 | [Dependency injection](12-DependencyInjection.md) | Pass dependencies (e.g. via constructor) instead of creating them inside the class. | DIP GoodCode: `NotificationService(NotificationChannel)` |
| 13 | [Fluent API & annotations](13-FluentAPIAndAnnotations.md) | Method chaining (`return this`); `@Override`, `@SuppressWarnings`. | Builder (fluent); all overrides (`@Override`) |

---

## Quick “where do I see this?”

- **Interfaces / implements / polymorphism** → `OCP/GoodCode/PaymentMethod.java`, `DIP/GoodCode/NotificationChannel.java`, `FactoryDesignPattern/GoodCode/Main.java`
- **Encapsulation / constructors / final** → `BuilderPattern/After/Main.java`, `SingletonDesignPattern/After/JudgeAnalytics.java`
- **static (main, factory, holder)** → Any `Main.java`, `LogisticsFactory.getLogistics()`, `JudgeAnalytics.getInstance()`
- **Generics** → `List<String>`, `Map<String, EmailTemplate>` in Builder and Prototype
- **Cloneable** → `PrototypeDesignPattern/After/Main.java` (`EmailTemplate`, `WelcomeEmail.clone()`)
- **Nested class** → `JudgeAnalytics.JudgeAnalyticsHolder`, `BurgerMeal.BurgerMealWithBuilder`
- **Dependency injection** → `DIP/GoodCode/NotificationService.java`
- **Exceptions** → `PrototypeDesignPattern/After` (clone), `LSP/BadCode` (`UnsupportedOperationException`)

Start with [01-Interfaces](01-Interfaces.md) and follow the list in order if you’re new to these concepts.
