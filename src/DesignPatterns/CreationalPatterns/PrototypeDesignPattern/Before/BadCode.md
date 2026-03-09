# Problem Analysis: Manual Instantiation & Singleton Misuse

## 1. High Creation Cost

In complex systems, constructors often perform "heavy lifting"—reading files, parsing XML/JSON, or querying databases.

- **The Issue:** Using the `new` keyword repeatedly triggers this expensive logic every time.
- **The Impact:** System performance drops when creating hundreds of similar objects (like email notifications).

## 2. Why Singleton Fails Here

A Singleton is a single, shared instance.

- **The Conflict:** If we used a Singleton for `WelcomeEmail`, calling `setContent()` on one object would overwrite the content for the entire application.
- **The Lesson:** Use **Singleton** when you want every part of the app to see the *same* data. Use **Prototype** when you want every part to start with the same *template* but modify it independently.

## 3. Tight Coupling

The client code is forced to use the `new` keyword with a concrete class (`new WelcomeEmail()`).

- **The Issue:** If you only have access to an interface (`EmailTemplate`), you cannot create a new instance of that object without knowing its specific class.
- **The Prototype Solution:** Prototype allows you to ask an existing object to "clone itself" without the client needing to know the specific class type.

