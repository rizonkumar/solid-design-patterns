package DesignPatterns.CreationalPatterns.AbstractFactoryDesignPattern.After;

// 1. ABSTRACT PRODUCTS: Define what we need (Payment and Invoice)
interface PaymentGateway {
    void processPayment(double amount);
}

interface Invoice {
    void generateInvoice();
}

// 2. CONCRETE PRODUCTS: Regional implementations
class RazorpayGateway implements PaymentGateway {

    public void processPayment(double amount) {
        System.out.println("Processing INR payment via Razorpay: " + amount);
    }
}

class GSTInvoice implements Invoice {

    public void generateInvoice() {
        System.out.println("Generating GST Invoice for India.");
    }
}

class PayPalGateway implements PaymentGateway {

    public void processPayment(double amount) {
        System.out.println("Processing USD payment via PayPal: " + amount);
    }
}

class USInvoice implements Invoice {

    public void generateInvoice() {
        System.out.println("Generating Invoice as per US norms.");
    }
}

// 3. THE ABSTRACT FACTORY: An interface to create families of related objects.
// It defines 'WHAT' to create, but not 'WHICH' concrete class to use.
interface RegionFactory {
    PaymentGateway createPaymentGateway(String gatewayType);
    Invoice createInvoice();
}

// 4. CONCRETE FACTORIES: These handle regional specifics.
// Note: These classes encapsulate the 'new' keyword and regional 'if-else' logic.
class IndiaFactory implements RegionFactory {

    public PaymentGateway createPaymentGateway(String gatewayType) {
        if (
            gatewayType.equalsIgnoreCase("razorpay")
        ) return new RazorpayGateway();
        // Add PayU logic here...
        throw new IllegalArgumentException("Unsupported gateway for India.");
    }

    public Invoice createInvoice() {
        return new GSTInvoice(); // Guaranteed consistency: India gets GST.
    }
}

class USFactory implements RegionFactory {

    public PaymentGateway createPaymentGateway(String gatewayType) {
        if (gatewayType.equalsIgnoreCase("paypal")) return new PayPalGateway();
        // Add Stripe logic here...
        throw new IllegalArgumentException("Unsupported gateway for US.");
    }

    public Invoice createInvoice() {
        return new USInvoice(); // Guaranteed consistency: US gets USInvoice.
    }
}

/**
 * 5. THE CLIENT: Decoupled and flexible.
 * CheckoutService no longer cares about specific regional classes.
 * It only knows about the 'RegionFactory' interface.
 */
class CheckoutService {

    private final PaymentGateway paymentGateway;
    private final Invoice invoice;

    // Dependency Injection: The factory is passed in, not created inside.
    public CheckoutService(RegionFactory factory, String gatewayType) {
        this.paymentGateway = factory.createPaymentGateway(gatewayType);
        this.invoice = factory.createInvoice();
    }

    public void completeOrder(double amount) {
        paymentGateway.processPayment(amount);
        invoice.generateInvoice();
    }
}

class Main {

    public static void main(String[] args) {
        // We decide the "Family" (India) at the entry point of the application.
        CheckoutService indiaCheckout = new CheckoutService(
            new IndiaFactory(),
            "razorpay"
        );
        indiaCheckout.completeOrder(1999.0);

        // Switching regions is as easy as passing a different Factory instance.
        CheckoutService usCheckout = new CheckoutService(
            new USFactory(),
            "paypal"
        );
        usCheckout.completeOrder(49.99);
    }
}
