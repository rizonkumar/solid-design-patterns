package SOLID.SRP.BadCode;

public class Invoice {

    private double amount;

    public Invoice(double amount) {
        this.amount = amount;
    }

    // Additional Functionality
    public void generateInvoice() {
        System.out.println("Invoice generated & printed for amount " + amount);
    }

    // this should be different class
    public void saveToDatabase() {
        System.out.println("Saving invoice to database");
    }

    // this should be different class
    public void sendEmailNotification() {
        System.out.println("Sending email notification for invoice");
    }
}
