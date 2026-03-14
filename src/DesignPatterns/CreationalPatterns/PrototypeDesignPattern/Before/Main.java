package DesignPatterns.CreationalPatterns.PrototypeDesignPattern.Before;

interface EmailTemplate {
    void setContent(String content);
    void send(String to);
}

class WelcomeEmail implements EmailTemplate {

    private String subject;
    private String content;

    public WelcomeEmail() {
        // Imagine this constructor involves heavy operations:
        // Loading large HTML/CSS templates, fetching branding from DB, etc.
        this.subject = "Welcome to our platform";
        this.content =
            "Welcome to our platform. We are glad to have you on board.";
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send(String to) {
        System.out.println(
            "Sending email to: " +
                to +
                "\nSubject: " +
                subject +
                "\nContent: " +
                content +
                "\n"
        );
    }
}

public class Main {

    public static void main(String[] args) {
        // ISSUE 1: Inefficiency
        // Calling 'new' multiple times re-runs the expensive constructor logic every single time.
        WelcomeEmail welcomeEmailPlus = new WelcomeEmail();
        welcomeEmailPlus.setContent(
            "Welcome to our plus platform. We are glad to have you on board."
        );

        WelcomeEmail welcomeEmailNonPlus = new WelcomeEmail();
        welcomeEmailNonPlus.setContent(
            "Welcome to our non-plus platform. We are glad to have you on board."
        );

        /* * WHY WE CAN'T USE SINGLETON HERE:
         * Singleton pattern ensures only ONE instance exists globally.
         * If we made WelcomeEmail a Singleton:
         * 1. Changing 'content' for 'welcomeEmailPlus' would also change it for 'welcomeEmailNonPlus'.
         * 2. We cannot have two different versions (Plus vs. Non-Plus) at the same time.
         * 3. Singleton is for "Global State"; Prototype is for "Independent Copies".
         */

        welcomeEmailPlus.send("premium_user@example.com");
        welcomeEmailNonPlus.send("free_user@example.com");
    }
}
