# Encapsulating Requests

**Behavioral design patterns** focus on how objects interact and communicate with each other, defining the flow of control in a system. These patterns make it easier to manage the interactions between objects, promoting loose coupling and enhancing flexibility.

Imagine a remote control that sends commands to various devices, like turning on the lights or adjusting the volume. The user doesn’t need to understand the internal workings of the devices, just the commands they can give. This is a perfect example of what behavioral patterns like the Command Pattern help us achieve.

---

## 1. What is the Command Pattern?

The **Command Pattern** is a behavioral design pattern that turns a request into a separate object, allowing you to decouple the code that issues the request from the code that performs it.

### Formal Definition

The Command Pattern encapsulates a request as an object, allowing for parameterization of clients with different requests, queuing of requests, and logging of the requests. It lets you add features like undo, redo, logging, and dynamic command execution without changing the core business logic.

This allows you to execute commands at a later time, in a flexible manner, without having to interact directly with the request's execution details.

### Real-Life Analogy

Think of a remote control used to turn on or off the lights or an air conditioner (AC). When you press a button to turn on the lights or adjust the temperature, you don’t need to understand how the internal circuits work or how the AC receives the signal. You just press the "On" or "Off" button, and the remote control takes care of sending the command.

Similarly, the Command Pattern decouples the sender of a request (the remote control) from the receiver (the light or AC), providing flexibility and simplicity in handling commands.

---

## 2. Four Key Components

- **Client**: Initiates the request and sets up the command object.
- **Invoker**: Asks the command to execute the request.
- **Command**: Defines an interface binding a receiver object to a specific action.
- **Receiver**: Knows how to perform the actual business logic to satisfy a request.

---

## 3. Understanding the Problem: Direct Execution Bloat

In a naive implementation, a control interface directly connects its trigger functions to concrete device systems.

### Issues in the Naive Code

- **Tight Coupling**: The `NaiveRemoteControl` class directly calls methods on the `Light` and `AC` classes. If additional devices need to be added in the future, changes will be required in the remote control class. This violates the **Open/Closed Principle (OCP)**.
- **Lack of Flexibility**: The commands are hardcoded in the remote control class. If new actions or different command sequences are required, modifying the remote control code is necessary, leading to potential maintenance challenges.
- **Brittle Undo Functionality**: The `pressUndo` method relies on a shallow string variable and a nested switch statement. This makes it difficult to add more complex undo functionality, especially when dealing with multiple actions or a variety of devices.
- **Hardcoded Commands**: The remote control class directly defines commands like `pressLightOn`, `pressACOn`, etc. This makes the system rigid and difficult to modify.
- **No Command History**: The original approach doesn’t have a centralized mechanism to track previously executed commands sequentially. This leads to difficulties in implementing multi-level undo operations efficiently.

---

## 4. Class Diagram & Structural Layout

The Command Pattern inserts an abstraction layer between the trigger slots and the device mechanics, turning workflows into separate polymorphic data objects.

```mermaid
classDiagram
    class Client {
    }
    class RemoteControl {
        -buttons: Command[]
        -commandHistory: Stack~Command~
        +setCommand(slot: int, command: Command) void
        +pressButton(slot: int) void
        +pressUndo() void
    }
    class Command {
        <<interface>>
        +execute() void
        +undo() void
    }
    class LightOnCommand {
        -light: Light
        +execute() void
        +undo() void
    }
    class LightOffCommand {
        -light: Light
        +execute() void
        +undo() void
    }
    class AConCommand {
        -ac: AC
        +execute() void
        +undo() void
    }
    class ACOffCommand {
        -ac: AC
        +execute() void
        +undo() void
    }
    class Light {
        +on() void
        +off() void
    }
    class AC {
        +on() void
        +off() void
    }

    Client ..> RemoteControl : uses
    Client ..> Command : creates
    RemoteControl o-- Command : HAS-A
    Command <|.. LightOnCommand : implements
    Command <|.. LightOffCommand : implements
    Command <|.. AConCommand : implements
    Command <|.. ACOffCommand : implements
    LightOnCommand --> Light : HAS-A
    LightOffCommand --> Light : HAS-A
    AConCommand --> AC : HAS-A
    ACOffCommand --> AC : HAS-A

## 5. How the Command Pattern Resolves the Issues

| Issue | How Command Pattern Resolves the Issue |
| :--- | :--- |
| **Tight Coupling** | The `RemoteControl` class no longer directly interacts with devices. It talks only to the `Command` interface, completely decoupling the invocation logic. |
| **Lack of Flexibility** | New actions or devices can be integrated as separate `Command` implementations without requiring edits to the `RemoteControl` class. |
| **Undo Functionality** | Provides a uniform contract for rolling back states. Each concrete command houses its own inverse `undo()` implementation, making reversals safe and isolated. |
| **Hardcoded Commands** | Employs an interface structure that supports dynamic runtime reassignment of different commands to any remote button slot. |
| **Maintaining Command History** | Introduces a real stack (`commandHistory`) to cleanly track previously executed operations, centralizing multi-step stepbacks. |

---

## 6. Impact Without the Command Pattern
* **Tight Coupling Between Invoker and Receiver**: The trigger mechanism and target devices remain hard-wired, breaking if class APIs or structure configurations shift.
* **Lack of Reusability**: Monolithic actions cannot be shared or called by other scheduling services or automated macros across the application without code duplication.
* **Complex Undo/Redo Operations**: State recovery becomes highly error-prone and complex as developers are forced to manually track mutations using strings or indices.
* **Difficulty in Implementing Batch Actions**: Packaging batch routines (e.g., a "Night Mode" setup that turns off lights and drops AC temperatures together) becomes cumbersome because actions cannot be nested as composites.
* **No Plug-and-Play Flexibility**: The control platform stays completely static and incapable of adapting to runtime profile changes or on-the-fly configurations.

---

## 7. Operational Trade-Offs

### Pros
* **Decouples Sender and Receiver**: Enhances flexibility and maintainability by isolating trigger sources from low-level device components.
* **Supports Clean Undo/Redo Functionality**: Natively tracks execution pipelines, facilitating easy structural reversals of state transactions.
* **Extensible and Reusable Architecture**: Fully aligns with the Open/Closed Principle, letting developers easily drop in fresh command sets without breaking existing code.

### Cons
* **Class Count Explosion**: Creates a larger volume of files because every distinct action branch demands its own concrete class structure.
* **Unnecessary Complexity for Simple Tasks**: Introducing interfaces and history stacks can be over-engineering for simple architectures that only trigger one or two invariant actions.
* **Requires Careful State Design for Undo/Redo**: Reversing operations accurately can demand comprehensive architectural care if commands rely on complex contextual states or shared historical footprints.

---

## 8. When to Use the Command Pattern
* **Decoupling Requirements**: When you need to keep the object executing a command completely blind to the entity that triggered it.
* **Undo/Redo Lifecycles**: When your framework demands reliable, step-by-step transaction rollbacks.
* **Macro Actions**: When you want to group multiple actions into single unified workflows (Composite commands) to execute complex, sequential operations together.
* **Plug-in System Design**: When you are building extensible architectures where new operations can be loaded and attached dynamically at runtime.
```
