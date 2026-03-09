package DesignPatterns.CreationalPatterns.AbstractFactoryDesignPattern.Before;

// 1. Product Interface for Payments
interface PaymentGateway {
    void processPayment(double amount);
}

// Concrete Product A1
class RazorpayGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via Razorpay: " + amount);
    }
}

// Concrete Product A2
class PayUGateway implements PaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing INR payment via PayU: " + amount);
    }
}

// 2. Product Interface for Invoices
interface Invoice {
    void generateInvoice();
}

// Concrete Product B1
class GSTInvoice implements Invoice {
    public void generateInvoice() {
        System.out.println("Generating GST Invoice for India.");
    }
}

/**
 * PROBLEM: The "God Class" that creates its own dependencies.
 * This class handles both the BUSINESS logic (checkout) and the CREATION logic (new keyword).
 */
class CheckoutService {
    private String gatewayType;

    public CheckoutService(String gatewayType) {
        this.gatewayType = gatewayType;
    }

    public void checkOut(double amount) {
        PaymentGateway paymentGateway;

        // PROBLEM 1: Tight Coupling & "If-Else" Hell
        // If we add a 3rd gateway (e.g., Stripe), we MUST modify this class.
        // This violates the Open/Closed Principle.
        if (gatewayType.equals("razorpay")) {
            paymentGateway = new RazorpayGateway();
        } else {
            paymentGateway = new PayUGateway();
        }

        paymentGateway.processPayment(amount);

        // PROBLEM 2: Lack of Cohesion (Families of objects)
        // What if we expand to the US? We would need a 'TaxInvoice' instead of 'GSTInvoice'.
        // Currently, we are hard-coding GSTInvoice here. There is no way to ensure the
        // PaymentGateway and Invoice "match" as a family.
        Invoice invoice = new GSTInvoice(); 
        invoice.generateInvoice();
    }
}

class Main {
    public static void main(String[] args) {
        CheckoutService razorpayService = new CheckoutService("razorpay");
        razorpayService.checkOut(1500.00);
    }
}