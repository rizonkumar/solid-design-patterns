package Basics.OOP;

public class Upi implements PaymentMethod{
    String upiId;

    Upi(String id) {
        this.upiId = id;
    }

    public void pay() {
        System.out.println("Making payment via UPI" + upiId);
    }
}
