package SOLID.OCP.GoodCode;

public class Main {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        PaymentMethod creditCard = new CreditCard();
        PaymentMethod upi = new UPI();

        processor.processPayment(creditCard, 100);
        processor.processPayment(upi, 200);
    }
}
