package Basics.JavaConcepts.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * One runnable class that demonstrates several Java concepts used in
 * DesignPatterns and SOLID. Run this after reading the JavaConcepts docs.
 *
 * Concepts shown: interface, implements, encapsulation, constructor,
 * static/final, polymorphism, generics, dependency injection.
 */
public class ConceptsDemo {

    // ----- 1. INTERFACE (contract) -----
    interface Notifier {
        void send(String msg);
    }

    // ----- 2. IMPLEMENTS + ENCAPSULATION (private field, getter) -----
    static class EmailNotifier implements Notifier {
        private final String from;

        public EmailNotifier(String from) {
            this.from = from;
        }

        @Override
        public void send(String msg) {
            System.out.println("[Email from " + from + "] " + msg);
        }
    }

    static class SMSNotifier implements Notifier {
        @Override
        public void send(String msg) {
            System.out.println("[SMS] " + msg);
        }
    }

    // ----- 3. DEPENDENCY INJECTION (dependency passed in constructor) -----
    static class AlertService {
        private final Notifier notifier;

        public AlertService(Notifier notifier) {
            this.notifier = notifier;
        }

        public void alert(String msg) {
            notifier.send(msg);
        }
    }

    // ----- 4. STATIC + GENERICS -----
    private static final List<String> MESSAGES = new ArrayList<>();

    static {
        MESSAGES.add("First");
        MESSAGES.add("Second");
    }

    public static void main(String[] args) {
        // Polymorphism: variable type is Notifier, actual object varies
        Notifier email = new EmailNotifier("alerts@app.com");
        Notifier sms = new SMSNotifier();

        email.send("Hello via email");
        sms.send("Hello via SMS");

        // Dependency injection: AlertService doesn't create Notifier, we pass it
        AlertService withEmail = new AlertService(email);
        AlertService withSMS = new AlertService(sms);
        withEmail.alert("Alert 1");
        withSMS.alert("Alert 2");

        // Generics: List<String>
        for (String m : MESSAGES) {
            System.out.println("Message: " + m);
        }
    }
}
