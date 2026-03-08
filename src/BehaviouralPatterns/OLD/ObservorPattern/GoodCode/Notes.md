# Observer Pattern — Good Code

## Overview

The improved code correctly applies the **Observer Pattern**, enabling a Weather Station (Subject) to notify multiple
devices (Observers) automatically whenever the temperature changes.

This removes tight coupling and makes the design flexible and extensible.

---

## How the Improved Design Works

### 1. Subject Interface

Defines the core behaviors every publisher must have:

- `attach()` → add a new observer
- `detach()` → remove an observer
- `notifyObservers()` → update all observers whenever data changes

This ensures the WeatherStation is generalized and can support any observer type.

---

### 2. Observer Interface

Any subscriber device must implement:

- `update(float temp)`

This allows the Weather Station to push updates without knowing *what* the observers are.

---

### 3. WeatherStationIn (Concrete Subject)

- Stores state (`temperature`)
- Maintains a list of observers
- Calls `notifyObservers()` whenever temperature changes

The station **does not know** how observers display the temperature → loose coupling.

---

### 4. DisplayDeviceIn and MobileDevice (Concrete Observers)

Each observer:

- Implements `update()`
- Defines its own display logic

When notified, they react differently but independently.

---

## Why This Fixes the Old Code Problems

### 1. Removes Tight Coupling

Old Code:

- WeatherStation had a hardcoded reference to a single `DisplayDevice`.

New Code:

- WeatherStation only knows the **Observer interface**, not the concrete types.

---

### 2. Supports Multiple Observers Easily

New observers can be added by calling:

```java
weatherStationIn.attach(new AnyObserver());