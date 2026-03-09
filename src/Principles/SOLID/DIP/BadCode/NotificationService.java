package SOLID.DIP.BadCode;

/**
 * Problem:
 * The NotificationService is tightly coupled with specific implementations
 * like EmailService and SMSService.
 * <p>
 * If the EmailService or SMSService changes, we must modify this class.
 * Also, if we add a new service (like WhatsApp), we need to create a new object
 * and update this class again.
 * <p>
 * This violates the Dependency Inversion Principle because the high-level
 * NotificationService depends directly on low-level modules instead of abstractions.
 */
public class NotificationService {
    // Direct dependencies on concrete classes
    private EmailService emailService;
    private SMSService smsService;

    // Constructor initializes specific implementations
    public NotificationService() {
        this.emailService = new EmailService();
        this.smsService = new SMSService();
    }

    public void notifyByEmail(String msg) {
        emailService.sendEmail(msg);
    }

    public void notifyBySMS(String msg) {
        smsService.sendSMS(msg);
    }
}
