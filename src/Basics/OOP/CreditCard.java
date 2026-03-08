package Basics.OOP;

public class CreditCard extends Card {

    public CreditCard(String cardNo, String name) {
        super(cardNo, name);
    }

    @Override
    public void pay() {
        System.out.println("Making Payment via Credit Card");
    }
}
