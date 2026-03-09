package Basics.OOP;

import java.util.HashMap;

public class PaymentService {
    // Storing + Making Payment
    HashMap<String, PaymentMethod> paymentMethod;
    PaymentService() {
        paymentMethod = new HashMap<>();
    }
    public void addPaymentMethod(String name, PaymentMethod pm) {
        paymentMethod.put(name, pm);
    }

    public void makePayment(String name) {
        PaymentMethod pm = paymentMethod.get(name);
        pm.pay(); // Run time Polymorphism
    }
}
