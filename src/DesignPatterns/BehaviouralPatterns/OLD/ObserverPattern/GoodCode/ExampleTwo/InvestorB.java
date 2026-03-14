// The InvestorB class implements the Observer interface and receives stock price updates.

package DesignPatterns.BehaviouralPatterns.OLD.ObserverPattern.GoodCode.ExampleTwo;

public class InvestorB implements Observer {

    @Override
    public void update(String stockSymbol, double newPrice) {
        System.out.println(
            "Investor B notified: Stock " +
                stockSymbol +
                " has a new price: $" +
                newPrice
        );
    }
}
