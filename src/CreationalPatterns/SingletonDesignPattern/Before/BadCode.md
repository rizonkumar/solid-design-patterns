# Problem Analysis: Multiple Instances, No Single Source of Truth

## 1. No Global State Guarantee

`JudgeAnalytics` is a simple class that can be instantiated anywhere with `new JudgeAnalytics()`.

- **The Issue:** Each caller gets its **own** instance. Run counts and submit counts are scattered across different objects.
- **The Impact:** There is no single, authoritative view of "total runs" or "total submits" for the entire system. One component may see 5 runs while another sees 2, depending on which instance it holds.

## 2. Violation of Single Responsibility for "Global" Data

Analytics that must represent **system-wide** behavior (e.g., total submissions to a judge) should have exactly one place that holds that state.

- **The Issue:** The current design allows unlimited instances. Any class that needs analytics creates its own `JudgeAnalytics` and updates it, so the "global" picture is never correct.
- **The Impact:** Reporting, dashboards, or rate-limiting logic that depend on total run/submit counts cannot rely on a single source of truth.

## 3. Wasted Resources and Inconsistency

Creating a new `JudgeAnalytics` in every service or controller leads to:

- **The Issue:** Redundant objects that each maintain their own counters. No coordination between them.
- **The Scenario:** `Main` (or multiple services) each do `new JudgeAnalytics()`. Clicks in one place do not reflect in another. You cannot answer "how many runs happened in total?" correctly.

## 4. Testing and Predictability

With multiple instances, tests and production code cannot depend on a shared state.

- **The Issue:** You cannot inject "the" analytics instance or assert on a global count, because there is no single instance to inject or observe.
- **The Impact:** Harder to test and reason about system-wide metrics.

## Summary of the "Smell"

When you have a class that **must** represent a single, system-wide resource (logger, config, connection pool, or global analytics) but you allow **unrestricted instantiation** with `new`, you should enforce a **Singleton** so that only one instance ever exists and is shared by all callers.
