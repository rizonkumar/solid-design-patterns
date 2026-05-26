package DesignPatterns.BehaviouralPatterns.NEW.TemplateMethodPattern.After;

import java.util.*;

// ============================================================================
// 1. ABSTRACT BASE CLASS (The Skeletal Framework)
// ============================================================================
/**
 * Serves as the global governor for the execution pipeline. It enforces the overall
 * structural sequence of the algorithm, leaving individual specific variations
 * to be implemented by its subclasses.
 */
abstract class NotificationSender {

    /**
     * THE TEMPLATE METHOD
     * Notice the 'final' keyword. This is critical in an LLD interview because it
     * prevents subclasses from overriding and breaking the master execution sequence.
     */
    public final void send(String to, String rawMessage) {
        // Step 1: Invariant boilerplate orchestration execution
        rateLimitCheck(to);
        validateRecipient(to);
        String formatted = formatMessage(rawMessage);
        preSendAuditLog(to, formatted);

        // Step 2: Abstract Hook Delegations (Variant mechanics managed by subclasses)
        String composedMessage = composeMessage(formatted);
        sendMessage(to, composedMessage);

        // Step 3: Optional Hook Execution (Provides custom override intervention)
        postSendAnalytics(to);
    }

    // --- INVARIANT STEPS (Shared Boilerplate Private Routines) ---
    private void rateLimitCheck(String to) {
        System.out.println("Checking rate limits for: " + to);
    }

    private void validateRecipient(String to) {
        System.out.println("Validating recipient: " + to);
    }

    private String formatMessage(String message) {
        return message.trim();
    }

    private void preSendAuditLog(String to, String formatted) {
        System.out.println("Logging before send: " + formatted + " to " + to);
    }

    // --- ABSTRACT PRIMITIVE HOOKS (Subclasses MUST implement these) ---
    protected abstract String composeMessage(String formattedMessage);

    protected abstract void sendMessage(String to, String message);

    // --- OPTIONAL HOOK (Provides baseline behavior, can be optionally overridden) ---
    protected void postSendAnalytics(String to) {
        System.out.println("Analytics updated for: " + to);
    }
}

// ============================================================================
// 2. CONCRETE IMPLEMENTATIONS (The Algorithmic Variants)
// ============================================================================
/**
 * Specializes strictly in text composition and network transport for Emails.
 * It is completely insulated from rate-limiting or workflow logging management.
 */
class EmailNotification extends NotificationSender {

    @Override
    protected String composeMessage(String formattedMessage) {
        return "<html><body><p>" + formattedMessage + "</p></body></html>";
    }

    @Override
    protected void sendMessage(String to, String message) {
        System.out.println(
            "Sending EMAIL to " + to + " with content:\n" + message
        );
    }
}

/**
 * Specializes strictly in SMS mechanics. It also targets the optional hook
 * to override default analytics behavior with a platform-specific routine.
 */
class SMSNotification extends NotificationSender {

    @Override
    protected String composeMessage(String formattedMessage) {
        return "[SMS] " + formattedMessage;
    }

    @Override
    protected void sendMessage(String to, String message) {
        System.out.println(
            "Sending SMS to " + to + " with message: " + message
        );
    }

    /**
     * OPTIONAL HOOK OVERRIDE
     * Overriding this allows the subclass to inject specialized analytics behavior
     * seamlessly without disrupting or modifying the core template execution path.
     */
    @Override
    protected void postSendAnalytics(String to) {
        System.out.println("Custom SMS analytics for: " + to);
    }
}

// ============================================================================
// 3. CLIENT CODE
// ============================================================================
class Main {

    public static void main(String[] args) {
        // Polymorphic declaration driven by the abstract template base type
        NotificationSender emailSender = new EmailNotification();
        emailSender.send("john@example.com", "Welcome to TUF+!");

        System.out.println(" ");

        NotificationSender smsSender = new SMSNotification();
        smsSender.send("9876543210", "Your OTP is 4567.");
    }
}
