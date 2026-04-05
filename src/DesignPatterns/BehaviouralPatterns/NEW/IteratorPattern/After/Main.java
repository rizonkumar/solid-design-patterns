package DesignPatterns.BehaviouralPatterns.NEW.IteratorPattern.After;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. THE AGGREGATE INTERFACE
 * This acts as a contract. Any collection that wants to be iterable
 * must be able to "create" an iterator for itself.
 */
interface Playlist {
    PlaylistIterator createIterator();
}

/**
 * 2. CONCRETE AGGREGATE
 * Stores the actual data. Notice it no longer has a 'getVideos()' method.
 * Encapsulation is now perfectly preserved.
 */
class YouTubePlaylist implements Playlist {
    // Internal data is now strictly private and hidden from the client
    private List<Video> videos = new ArrayList<>();

    public void addVideo(Video video) {
        videos.add(video);
    }

    @Override
    public PlaylistIterator createIterator() {
        // We pass the internal list to a new iterator instance
        return new YouTubePlaylistIterator(videos);
    }
}

/**
 * 3. THE ITERATOR INTERFACE
 * Defines the standard operations for traversing ANY collection
 * without knowing how that collection is stored.
 */
interface PlaylistIterator {
    boolean hasNext(); // Is there another element?

    Video next(); // Give me the next element and move the pointer.
}

/**
 * 4. CONCRETE ITERATOR
 * This class carries the "State" of the traversal (the 'position' variable).
 * This allows multiple clients to iterate over the SAME playlist simultaneously
 * because each has its own Iterator instance with its own 'position'.
 */
class YouTubePlaylistIterator implements PlaylistIterator {
    private List<Video> videos;
    private int position;

    public YouTubePlaylistIterator(List<Video> videos) {
        this.videos = videos;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < videos.size();
    }

    @Override
    public Video next() {
        // Standard safety check before returning data
        return hasNext() ? videos.get(position++) : null;
    }
}

public class Main {
    public static void main(String[] args) {
        YouTubePlaylist playlist = new YouTubePlaylist();
        playlist.addVideo(new Video("LLD Tutorial"));
        playlist.addVideo(new Video("System Design Basics"));

        /**
         * CLIENT CODE REVOLUTION:
         * 1. The client doesn't know if 'playlist' uses a List, Array, or Map.
         * 2. The client doesn't manage indices (i=0; i < size).
         * 3. The client cannot accidentally call .clear() on the internal list.
         */
        PlaylistIterator iterator = playlist.createIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next().getTitle());
        }
    }
}
