# Problem Analysis: The Telescoping Constructor

## The "Anti-Pattern"

The current implementation uses a **Telescoping Constructor**. As the `BurgerMeal` class grows to include `cheese`, `sauce`, or `isTakeout`, the constructor expands horizontally, making the code brittle.

### 1. Readability & Parameter Confusion

- **Issue:** `new BurgerMeal("Wheat", "Beef", "Fries", null, true)` is hard to read.
- **Risk:** Because many parameters are `String` types, it is extremely easy to swap "Wheat" and "Beef" accidentally. The compiler will not catch this error.

### 2. The "Forced Argument" Tax

- **Issue:** The client is forced to provide values for every parameter, even if they don't want them.
- **Code Smell:** Passing `null` or `"none"` clutters the `main` method and increases the risk of `NullPointerException`.

### 3. Maintenance Rigidity

- **Inflexibility:** To allow a burger with _just_ bread, you would need to create a new overloaded constructor. This leads to "Constructor Bloat."
- **Breaking Changes:** Adding one new field to the constructor breaks every existing instantiation of `BurgerMeal` in the entire project.

### 4. Technical Bug (Type Mismatch)

- **Error:** `The constructor BurgerMeal(String, String, String, String) is undefined`.
- **Reason:** The constructor expects a `List<String>` as the 4th argument. Passing `"null"` (a String) instead of `null` (the reference) creates a signature mismatch.
