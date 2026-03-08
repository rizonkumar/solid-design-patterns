package Basics.OOP;

public class Wallet implements PaymentMethod{
    @Override
    public void pay() {
        System.out.println("making payment with wallet");
    }
}
