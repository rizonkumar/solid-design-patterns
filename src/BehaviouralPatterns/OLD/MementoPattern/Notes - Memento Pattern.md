# Memento Pattern — Short Notes

## Definition

The **Memento Pattern** is a **behavioral design pattern** that allows an object to **save and restore its previous
state** without exposing its internal details.

It helps implement **undo/redo** functionality while maintaining **encapsulation**.

---

## Key Components

1. **Originator** – The object whose state needs to be saved (e.g., `TextEditor`).
2. **Memento** – Stores the snapshot of the Originator's state.
3. **Caretaker** – Manages the mementos (saves/restores them without modifying).

---

## Benefits

- Enables undo/redo functionality cleanly.
- Preserves encapsulation — internal state remains private.
- Simplifies state management in applications like editors, games, or transactions.

---

## Real-world Example

- Text editors (Undo/Redo).
- Version control systems.
- Graphic design tools with history tracking.
