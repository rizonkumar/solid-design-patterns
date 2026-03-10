package DesignPatterns.StructuralPatterns.AdapterDesignPattern.After;

/**
 * 1. TARGET INTERFACE
 * This defines the "domain-specific" interface that the Client (CheckoutService) uses.
 */
interface PaymentGateway {
    void pay(String orderId, double amount);
}

/**
 * 2. COMPATIBLE IMPLEMENTATION
 * A standard implementation that already follows our internal contract.
 */
class PayUGateway implements PaymentGateway {
    @Override
    public void pay(String orderId, double amount) {
        System.out.println("Paid Rs. " + amount + " using PayU for order: " + orderId);
    }
}

/**
 * 3. ADAPTEE (The External Legacy/Third-Party System)
 * This class has the functionality we need, but its interface is incompatible 
 * with our PaymentGateway (different method names/parameters).
 */
class RazorpayAPI {
    public void makePayment(String invoiceId, double amountInRupees) {
        System.out.println("Paid Rs. " + amountInRupees + " using Razorpay for invoice: " + invoiceId);
    }
}

/**
 * 4. THE ADAPTER
 * This is the "Wrapper." It implements the Target interface and delegates 
 * the work to the Adaptee (RazorpayAPI).
 */
class RazorpayAdapter implements PaymentGateway {
    private RazorpayAPI razorpayAPI;

    public RazorpayAdapter() {
        this.razorpayAPI = new RazorpayAPI();
    }

    /**
     * TRANSLATION LAYER:
     * This method maps our standard 'pay()' call to the third-party 'makePayment()' call.
     */
    @Override
    public void pay(String orderId, double amount) {
        // We translate orderId -> invoiceId and amount -> amountInRupees
        razorpayAPI.makePayment(orderId, amount);
    }
}

/**
 * 5. CLIENT
 * Stays 100% decoupled. It doesn't know (or care) if it's talking to 
 * PayU directly or to Razorpay via an Adapter.
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

class Main {
    public static void main(String[] args) {
        // SUCCESS: We can now plug the 'Adapter' into the 'Service'.
        // This follows the Open/Closed Principle: We added Razorpay 
        // without modifying the CheckoutService class.
        CheckoutService checkoutService = new CheckoutService(new RazorpayAdapter());
            
        checkoutService.checkout("12", 1780);
    }
}