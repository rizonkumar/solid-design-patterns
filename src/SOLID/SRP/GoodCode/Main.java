package SOLID.SRP.GoodCode;

/**
 * Good: Invoice only generates; repository saves; email service sends. Single responsibility each.
 */
public class Main {
    public static void main(String[] args) {
        Invoice invoice = new Invoice(100.0);
        invoice.generateInvoice();

        InvoiceRepository repository = new InvoiceRepository();
        repository.saveToDatabase();

        EmailService emailService = new EmailService();
        emailService.sendEmailNotification();
    }
}
