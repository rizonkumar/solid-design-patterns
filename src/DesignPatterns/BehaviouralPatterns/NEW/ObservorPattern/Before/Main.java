package DesignPatterns.BehavioralPatterns.NEW.ObservorPattern.Before;

/**
 * PROBLEM: Tight Coupling & Lack of Scalability.
 * The 'YoutubeChannel' (Subject) is directly responsible for knowing WHO
 * needs a notification and HOW to send it.
 */
class YoutubeChannel {

    public void uploadNewVideo(String videoTitle) {
        System.out.println("Uploading new video: " + videoTitle);

        /**
         * ISSUE 1: HARD-CODED SUBSCRIBERS
         * Every time a new person hits the 'Subscribe' button, you have to
         * manually add a line of code here. This violates the Open/Closed Principle.
         */
        System.out.println("Sending email to rizon.kumar.rahi@gmail.com");

        /**
         * ISSUE 2: VARYING NOTIFICATION METHODS
         * What if one user wants Email and another wants SMS?
         * The YoutubeChannel class will soon be bloated with logic for
         * various notification protocols, violating the Single Responsibility Principle.
         */
        System.out.println("Sending in-app notification to user@gmail.com");
    }
}

public class Main {

    public static void main(String[] args) {
        YoutubeChannel channel = new YoutubeChannel();

        // ISSUE 3: NO RUNTIME FLEXIBILITY
        // There is no way for a user to "Unsubscribe" without deleting code.
        channel.uploadNewVideo("Design Patterns in JAVA");
    }
}
