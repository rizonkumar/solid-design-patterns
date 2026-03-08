# Memento Pattern — Good Code Explanation

## Purpose Recap

The goal of the **Memento Pattern** is to allow an object to **save and restore its state** without exposing its
internal structure or breaking encapsulation.

This makes it ideal for features like **Undo / Redo** in applications such as text editors, games, and drawing tools.

## Class Responsibilities (Overview)

### 1. TextEditor (Originator)

- Represents the object whose state needs to be saved.
- Knows how to create a snapshot (`save()`) and how to restore it (`restore()`).
- Does **not** know who stores the snapshots.

### 2. EditorMemento (Memento)

- Stores the **snapshot of the editor’s state** (the text content in this case).
- Immutable: once created, its state cannot be changed.
- Provides a getter (`getContent()`) to allow the `TextEditor` to restore its state when needed.

### 3. Caretaker

- Manages the history (stack of mementos).
- Responsible for **saving** and **restoring** states.
- Does not modify or directly access the internal data of `TextEditor` or `EditorMemento`.
- Acts as the "history manager" for undo/redo operations.

### 4. TextEditorMain

- Demonstrates how to use the pattern.
- Creates the editor and caretaker, saves states, modifies content, and performs undo operations.

## How It Works (Step-by-Step Flow)

1. **Initial State Creation**
   ```java
   editor.write("Hello World");
   caretaker.saveState(editor);


* The editor writes some text.
* `saveState(editor)` calls `editor.save()`, which creates a new `EditorMemento` containing `"Hello World"`.
* This snapshot is pushed into the caretaker’s `history` stack.

2. **State Change**

   ```java
   editor.write("Hello Everyone");
   caretaker.saveState(editor);
   ```

    * The editor writes new content.
    * The new state is again saved to the caretaker stack.

3. **Undo Operation**

   ```java
   caretaker.undo(editor);
   ```

    * The caretaker pops the most recent state.
    * Then it restores the previous one using `editor.restore(memento)`.

4. **Final Output**

   ```java
   System.out.println(editor.getContent());
   ```

    * Displays `"Hello World"`, showing that the undo successfully reverted the last change.

---

## How This Fixes the Old Code

| Problem in Old Code                        | How Good Code Fixes It                                                                                     |
|--------------------------------------------|------------------------------------------------------------------------------------------------------------|
| No way to store previous states            | The `EditorMemento` class now stores snapshots of each state.                                              |
| `TextEditor` risked exposing internal data | The state is encapsulated inside `EditorMemento`, maintaining data privacy.                                |
| Undo/Redo logic was missing                | The `Caretaker` manages state history using a stack and provides undo functionality.                       |
| Violated SRP (Single Responsibility)       | `TextEditor` only handles editing logic, while `Caretaker` handles history — clear separation of concerns. |

---

## Key Advantages of This Design

1. **Encapsulation is preserved**

    * The internal state of `TextEditor` remains hidden; only `Memento` can store it.
2. **Easy to extend**

    * Adding redo functionality or multi-level undo becomes straightforward.
3. **SRP compliance**

    * Each class handles only one concern: editor logic, state storage, or history management.
4. **Reusability**

    * The same `Caretaker` mechanism can be used for other objects needing state tracking.

---

## Summary

The improved code correctly applies the **Memento Pattern** by separating responsibilities among three components:

* `TextEditor` → Originator
* `EditorMemento` → Memento (state holder)
* `Caretaker` → State manager (undo/redo handler)

This design provides **undo functionality** while keeping the **internal state private**, **code maintainable**, and *
*extensible** — fully addressing the shortcomings of the old implementation.

