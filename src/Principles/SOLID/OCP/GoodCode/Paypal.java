package SOLID.OCP.GoodCode;

public class Paypal implements PaymentMethod{

    @Override
    public void pay(double amount) {
        System.out.println("Making payment via paypal" + amount);
    }
}
