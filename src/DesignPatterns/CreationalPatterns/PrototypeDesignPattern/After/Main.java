package DesignPatterns.CreationalPatterns.PrototypeDesignPattern.After;

import java.util.*;

/**
 * 1. PROTOTYPE INTERFACE
 * Extends Cloneable to tap into Java's native memory-copying capabilities.
 */
interface EmailTemplate extends Cloneable {
    // Returns a copy of itself. This is faster than 'new' because it 
    // copies the object state directly in memory.
    EmailTemplate clone(); 
    void setContent(String content);
    void send(String to);
}

/**
 * 2. CONCRETE PROTOTYPE
 * Implements the clone logic. This class knows how to copy itself.
 */
class WelcomeEmail implements EmailTemplate {
    private String subject;
    private String content;

    public WelcomeEmail() {
        // This heavy setup (DB calls, CSS loading) happens ONLY ONCE
        // when the initial prototype is created in the Registry.
        this.subject = "Welcome to TUF+";
        this.content = "Hi there! Thanks for joining us.";
    }

    @Override
    public WelcomeEmail clone() {
        try {
            // super.clone() performs a "Shallow Copy". 
            // For complex objects with nested lists, you'd perform a "Deep Copy" here.
            return (WelcomeEmail) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone failed", e);
        }
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void send(String to) {
        System.out.println("Sending to " + to + ": [" + subject + "] " + content);
    }
}

/**
 * 3. PROTOTYPE REGISTRY (Optional but Recommended)
 * Stores "Master Copies" of your objects. Instead of the client 
 * knowing concrete classes, they just ask the Registry for a type.
 */
class EmailTemplateRegistry {
    private static final Map<String, EmailTemplate> templates = new HashMap<>();

    static {
        // Initialize the 'Master' objects once. 
        templates.put("welcome", new WelcomeEmail());
    }

    public static EmailTemplate getTemplate(String type) {
        // NEVER return the original from the map. 
        // Always return a clone so the original stays 'pure'.
        return templates.get(type).clone(); 
    }
}

class Main {
    public static void main(String[] args) {
        // Client doesn't use 'new' or know about 'WelcomeEmail' class.
        // It simply clones a pre-configured template.
        EmailTemplate welcomeEmail1 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail1.setContent("Hi Alice, welcome to TUF Premium!");
        welcomeEmail1.send("alice@example.com");

        EmailTemplate welcomeEmail2 = EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail2.setContent("Hi Bob, thanks for joining!");
        welcomeEmail2.send("bob@example.com");

        /* * SUCCESS: alice@example.com and bob@example.com have independent 
         * objects, but we avoided the expensive setup costs of 'new'.
         */
    }
}