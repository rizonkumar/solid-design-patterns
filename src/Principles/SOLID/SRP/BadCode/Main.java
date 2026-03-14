package SOLID.SRP.BadCode;

/**
 * Bad: Invoice does too much (generate, save, send email). Run this to see the coupled behavior.
 */
public class Main {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Invoice invoice = new Invoice(100.0);
        invoice.generateInvoice();
        invoice.saveToDatabase();
        invoice.sendEmailNotification();
    }
}
