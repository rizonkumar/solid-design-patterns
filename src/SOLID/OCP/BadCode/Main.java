package SOLID.OCP.BadCode;

/**
 * Bad: Adding a new payment method requires changing PaymentProcessor (if/else).
 */
public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.processPayment("CreditCard", 100);
        processor.processPayment("Paypal", 200);
    }
}
