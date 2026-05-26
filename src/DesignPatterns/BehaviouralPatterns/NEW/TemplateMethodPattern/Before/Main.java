package DesignPatterns.BehaviouralPatterns.NEW.TemplateMethodPattern.Before;

/**
 * PROBLEM: Code Duplication & Lack of Structural Enforcements.
 * EmailNotification and SMSNotification handle identical systemic steps (Rate Limits, Logging,
 * Validation, Analytics) manually. If the corporate security policy changes to require
 * an encryption phase before logging, every single distinct notification class must be modified.
 */
class EmailNotification {

    public void send(String to, String message) {
        // ISSUE 1: RIGID BOILERPLATE REPETITION
        System.out.println("Checking rate limits for: " + to);
        System.out.println("Validating email recipient: " + to);
        String formatted = message.trim();
        System.out.println("Logging before send: " + formatted + " to " + to);

        // ISSUE 2: VARIANT STEPS BLENDED WITH INVARIANT FLOW
        // The only part truly unique to Email is how it is composed and delivered.
        String composedMessage =
            "<html><body><p>" + formatted + "</p></body></html>";
        System.out.println(
            "Sending EMAIL to " + to + " with content:\n" + composedMessage
        );

        // ISSUE 3: ENFORCEMENT GAP
        // There is no mechanism guaranteeing that every implementation remembers to track analytics.
        System.out.println("Analytics updated for: " + to);
    }
}

class SMSNotification {

    public void send(String to, String message) {
        // REPEATED LOGIC: Duplicate rate limits, trimming, and logging mechanics.
        System.out.println("Checking rate limits for: " + to);
        System.out.println("Validating phone number: " + to); // Slightly different verification
        String formatted = message.trim();
        System.out.println("Logging before send: " + formatted + " to " + to);

        // SMS Variant mechanics
        String composedMessage = "[SMS] " + formatted;
        System.out.println(
            "Sending SMS to " + to + " with message: " + composedMessage
        );

        // Subtly different analytics logic, risking fragmented tracking reporting across channels.
        System.out.println("Custom SMS analytics for: " + to);
    }
}

class Main {

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        SMSNotification smsNotification = new SMSNotification();

        emailNotification.send(
            "example@example.com",
            "Your order has been placed!"
        );
        System.out.println(" ");
        smsNotification.send("1234567890", "Your OTP is 1234.");
    }
}
