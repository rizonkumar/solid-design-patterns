# Stock Price Monitoring System — Explanation

## Goal

Implement an Observer-based stock monitoring system where investors (observers) are notified when a stock's price
changes by more than a configured percentage threshold.

---

## Main components

1. **Subject (StockMarket)**
    - Maintains a list of `Observer` objects (`observers` list).
    - Exposes methods:
        - `registerObserver(Observer o)` — add an observer.
        - `removeObserver(Observer o)` — remove an observer.
        - `notifyObservers(String stockSymbol, double newPrice)` — inform all registered observers.
    - Business method:
        - `setStockPrice(String stockSymbol, double newPrice, double oldPrice)` — calculates the percent change and
          calls `notifyObservers(...)` **only if** the change meets or exceeds the configured `priceChangeThreshold`.

2. **Observer (InvestorA, InvestorB)**
    - Implement the `Observer` interface with `update(String stockSymbol, double newPrice)`.
    - Each observer decides what to do when notified (here we print a message showing the notification).

3. **Exercise (driver)**
    - Reads input:
        - `priceChangeThreshold` (double)
        - number of updates `updates`
        - for each update: `stockSymbol newPrice oldPrice`
    - Registers InvestorA and InvestorB as observers.
    - Calls `stockMarket.setStockPrice(...)` for each update.
    - Removes InvestorB after the 4th update (i.e., when `i == 5`), so InvestorB will not receive notifications from the
      5th update onward.

---

## Key behaviors and rationale

- **Threshold-based notification**
    - `setStockPrice(...)` computes percentage change:
      ```
      priceChange = abs(newPrice - oldPrice) / oldPrice * 100
      ```
    - Observers are notified only if `priceChange >= priceChangeThreshold`. This avoids spamming observers for
      insignificant fluctuations.

- **Observer registration & removal**
    - Observers register via `registerObserver(...)` and can be removed with `removeObserver(...)`.
    - Removing an observer prevents it from receiving future notifications.

- **Loose coupling**
    - `StockMarket` depends on the `Observer` interface, not concrete investor classes. This allows adding new types of
      observers without changing `StockMarket`.

- **Safe add/remove**
    - `registerObserver` checks for null and duplicate registrations (simple guard), while `removeObserver` uses
      `List.remove(...)`.

---

## Example input-output flow (conceptual)

Assume threshold = `2.0` (2%), and updates = 6:

1. Update 1: AAPL 150.0 147.0 → change ≈ 2.04% → notify A & B
2. Update 2: GOOGL 2800.0 2795.0 → change ≈ 0.18% → no notify
3. Update 3: TSLA 900.0 850.0 → change ≈ 5.88% → notify A & B
4. Update 4: AMZN 3400.0 3350.0 → change ≈ 1.49% → no notify
5. (i == 5) remove InvestorB
   Update 5: NFLX 600.0 550.0 → change ≈ 9.09% → notify Investor A only
6. Update 6: MSFT 330.0 320.0 → change ≈ 3.125% → notify Investor A only

The console output will show which investors were notified for each significant change.

---

## Extensibility notes

- To add another observer type, implement `Observer` and call `stockMarket.registerObserver(new ObserverType())`.
- To track multiple stocks with persistent state, `StockMarket` could maintain a map of `stockSymbol -> currentPrice`
  and compute oldPrice internally.
- For thread-safety (concurrent updates/observers), switch `ArrayList` to a thread-safe list or synchronize access to
  `observers`.