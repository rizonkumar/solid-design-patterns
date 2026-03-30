// The InvestorA class implements the Observer interface and receives stock price updates.

package DesignPatterns.BehaviouralPatterns.OLD.ObserverPattern.GoodCode.ExampleTwo;

public class InvestorA implements Observer {

    @Override
    public void update(String stockSymbol, double newPrice) {
        System.out.println(
            "Investor A notified: Stock " +
                stockSymbol +
                " has a new price: $" +
                newPrice
        );
    }
}
