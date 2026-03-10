package DesignPatterns.StructuralPatterns.AdapterDesignPattern.Before;

// 1. TARGET INTERFACE: The standard our system expects.
interface PaymentGateway {
  void pay(String orderId, double amount);
}

// 2. COMPATIBLE IMPLEMENTATION: Works perfectly with our service.
class PayUGateway implements PaymentGateway {
  @Override
  public void pay(String orderId, double amount) {
      System.out.println("Paid Rs. " + amount + " using PayU for order: " + orderId);
  }
}

/**
 * 3. ADAPTEE: The incompatible class.
 * It has the logic we want, but the interface is different.
 * Instead of 'pay', it uses 'makePayment'.
 * Instead of 'orderId', it uses 'invoiceId'.
 */
class RazorpayAPI {
    public void makePayment(String invoiceId, double amountInRupees) {
        System.out.println("Paid Rs. " + amountInRupees + " using Razorpay for invoice: " + invoiceId);
    }
}

/**
 * 4. CLIENT: The service that depends on the Target Interface.
 */
class CheckoutService {
    private PaymentGateway paymentGateway;

    public CheckoutService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void checkout(String orderId, double amount) {
        paymentGateway.pay(orderId, amount);
    }
}

public class Main {
      public static void main(String[] args) {
        // Works fine
        CheckoutService payUService = new CheckoutService(new PayUGateway());
        payUService.checkout("12", 1780);

        // PROBLEM: We cannot use Razorpay here!
        // CheckoutService razorpayService = new CheckoutService(new RazorpayAPI()); 
        // ^ This will cause a COMPILATION ERROR because RazorpayAPI does not 
        // implement the PaymentGateway interface.
    }
}