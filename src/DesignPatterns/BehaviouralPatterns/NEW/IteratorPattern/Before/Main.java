package DesignPatterns.BehavioralPatterns.NEW.IteratorDesignPattern.Before;

import java.util.ArrayList;
import java.util.List;

// A simple Video class with title
class Video {
    String title;

    public Video(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

/**
 * PROBLEM: Encapsulation Breach.
 * The playlist exposes its internal data structure (ArrayList) to the outside
 * world.
 */
class YouTubePlaylist {
    // INTERNAL STATE: What if we want to change this to a Set or a LinkedHashMap
    // later?
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video) {
        videos.add(video);
    }

    /**
     * ISSUE 1: EXPOSING THE DATA STRUCTURE
     * By returning 'List<Video>', you allow the client to know EXACTLY how
     * data is stored. If you change 'List' to 'Map', every client using this method
     * breaks.
     * * ISSUE 2: LACK OF CONTROL
     * The client can now call playlist.getVideos().clear() and empty your
     * playlist without permission.
     */
    public List<Video> getVideos() {
        return videos;
    }
}

// Client Code
class Main {
    public static void main(String[] args) {
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));

        /**
         * ISSUE 3: TIGHT COUPLING
         * The client is responsible for the "How" of traversal.
         * If the playlist were a complex tree structure, the client
         * would need to implement a complex Depth-First Search here.
         */
        for (Video v : playlist.getVideos()) {
            System.out.println(v.getTitle());
        }
    }
}
