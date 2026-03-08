# Why the Old Code Violates Memento Pattern Principles

## Problem Overview

The current `TextEditor` class directly holds the content and modifies it through `write()`.  
However, it does **not provide any mechanism** to:

- Save previous states.
- Restore older versions of the content.

This makes it impossible to perform **undo/redo** operations.

---

## Design Issues

1. **No State Preservation**
    - Every time `write()` is called, the old content is lost.
    - There is no way to go back to a previous state.

2. **Encapsulation Violation Risk**
    - If we try to add undo/redo directly in `TextEditor`, we would need to expose internal state details.
    - This would violate encapsulation and the **Single Responsibility Principle (SRP)** — the editor should not handle
      history management.

3. **Tight Coupling**
    - Managing history inside `TextEditor` ties editing logic with state management, making the class harder to maintain
      or extend.

---

## Core Problem

> How to provide undo/redo functionality or state restoration **without exposing the internal state** of `TextEditor`
> and **without violating SRP**.

The **Memento Pattern** solves this by introducing:

- A separate `Memento` class to hold snapshots of the editor’s state.
- A `Caretaker` (e.g., `History` class) to manage the mementos.

This keeps responsibilities clean and encapsulation intact.
