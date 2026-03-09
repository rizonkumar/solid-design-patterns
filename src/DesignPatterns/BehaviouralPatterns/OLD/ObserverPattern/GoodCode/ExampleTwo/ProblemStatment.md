# Stock Price Monitoring System Application Exercise

You are developing a stock price monitoring system that implements the **Observer Design Pattern** to notify investors (observers) whenever there is a significant change in stock prices (subject). The system should allow multiple investors to track different stocks and get notified about price fluctuations.

## Requirements

### Subject (Stock Market)
- The stock market should maintain a list of observers (investors)
- It should have methods to register, remove, and notify observers
- The `setStockPrice` method should update the stock price for a specific stock and notify all registered observers only if the price change exceeds a predefined threshold

### Observers
- Create two observer classes (e.g., `InvestorA` and `InvestorB`) that implement a common Observer interface
- Each observer should implement an `update` method to receive notifications about stock price changes

### Data Representation
- Each stock can have attributes like stock symbol, current price, and a threshold for notification (e.g., a percentage change)

## Output
- The program will register two observers (`InvestorA` and `InvestorB`)
- It will notify both observers of significant stock price changes that exceed the predefined threshold
- If an observer is removed, they will no longer receive notifications for subsequent price changes
- The output will confirm which investors received notifications, showcasing the observer design pattern in action

## Instructions
- You only need to complete the TODOs mentioned in the code
- Please do not modify any existing code outside of the specified TODO sections