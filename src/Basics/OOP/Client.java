package Basics.OOP;

public class Client {
    public static void main(String[] args) {
        PaymentService ps = new PaymentService();
        ps.addPaymentMethod("Rizon Kumar", new DebitCard("1234", "Rizon Card"));
        ps.addPaymentMethod("Rizon Credit Card", new CreditCard("2345", "Rizon Credit"));
        ps.addPaymentMethod("RizonUPI", new Upi("rizon13"));
        ps.makePayment("RizonUPI");
    }
}
