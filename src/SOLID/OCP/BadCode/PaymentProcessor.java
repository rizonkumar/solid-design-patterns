package SOLID.OCP.BadCode;

public class PaymentProcessor {
    public void processPayment(String paymentMethod, double amount) {
        if(paymentMethod.equals("CreditCard")) {
            // business logic
            System.out.println("Payment via credit card: " + amount);
        }
        else if(paymentMethod.equals("DebitCard")) {
            // business logic
            System.out.println("Payment via debit card: " + amount);
        }
        else if(paymentMethod.equals("Paypal")) {
            // business logic
            System.out.println("Payment via paypal: " + amount);
        }
        else {
            throw new IllegalArgumentException("Unsupported payment method" + paymentMethod);
        }
    }
}
