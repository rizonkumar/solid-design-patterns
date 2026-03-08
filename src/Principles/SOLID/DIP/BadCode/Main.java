package SOLID.DIP.BadCode;

/**
 * Bad: NotificationService is tightly coupled to EmailService and SMSService.
 * Adding a new channel would require modifying NotificationService.
 */
public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        service.notifyByEmail("Your order has been shipped");
        service.notifyBySMS("OTP is 1234");
    }
}
