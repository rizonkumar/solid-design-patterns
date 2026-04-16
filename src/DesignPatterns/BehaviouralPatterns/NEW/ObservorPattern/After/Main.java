package DesignPatterns.BehavioralPatterns.NEW.ObservorPattern.After;

import java.util.*;

/**
 * 1. THE OBSERVER INTERFACE
 * This defines the 'Contract'. Any object that wants to listen to
 * the channel must implement this interface.
 */
interface Subscriber {
    // The subject calls this method to push updates to the observer
    void update(String videoTitle);
}

/**
 * 2. CONCRETE OBSERVERS
 * These classes implement the actual notification logic (Email, SMS, App).
 * The Subject doesn't need to know these classes exist.
 */
class EmailSubscriber implements Subscriber {

    private String email;

    public EmailSubscriber(String email) {
        this.email = email;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println(
            "Email sent to " + email + ": New video uploaded - " + videoTitle
        );
    }
}

class MobileAppSubscriber implements Subscriber {

    private String username;

    public MobileAppSubscriber(String username) {
        this.username = username;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println(
            "In-app notification for " +
                username +
                ": New video - " +
                videoTitle
        );
    }
}

/**
 * 3. THE SUBJECT INTERFACE
 * Defines how to manage the lifecycle of a subscription.
 */
interface Channel {
    void subscribe(Subscriber subscriber);
    void unsubscribe(Subscriber subscriber);
    void notifySubscribers(String videoTitle);
}

/**
 * 4. CONCRETE SUBJECT
 * Maintains a dynamic list of observers. It is now completely agnostic
 * to how notifications are sent.
 */
class YouTubeChannel implements Channel {

    // Dynamic list allows adding/removing subscribers at runtime
    private List<Subscriber> subscribers = new ArrayList<>();
    private String channelName;

    public YouTubeChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String videoTitle) {
        // Broadcasters the update to everyone on the list
        for (Subscriber subscriber : subscribers) {
            subscriber.update(videoTitle);
        }
    }

    /**
     * BUSINESS LOGIC
     * When the state changes (upload), we simply trigger the notification loop.
     */
    public void uploadVideo(String videoTitle) {
        System.out.println(channelName + " uploaded: " + videoTitle + "\n");
        notifySubscribers(videoTitle);
    }
}

class Main {

    public static void main(String[] args) {
        YouTubeChannel tuf = new YouTubeChannel("takeUforward");

        // RUNTIME FLEXIBILITY:
        // We can create and add any number of different observers
        // without ever touching the YouTubeChannel class again.
        tuf.subscribe(new MobileAppSubscriber("raj"));
        tuf.subscribe(new EmailSubscriber("rahul@example.com"));

        tuf.uploadVideo("Observer-Pattern-Tutorial");

        // Dynamic Unsubscribe
        // tuf.unsubscribe(someSubscriber);
    }
}
