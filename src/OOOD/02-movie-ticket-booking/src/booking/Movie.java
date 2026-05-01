package booking;

import java.time.Duration;

public class Movie {
    private final String title;
    private final String genre;
    private final int durationInMinutes;

    public Movie(String title, String genre, int durationInMinutes) {
        this.title = title;
        this.genre = genre;
        this.durationInMinutes = durationInMinutes;
    }

    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getDurationInMinutes() { return durationInMinutes; }

    public Duration getDuration() {
        return Duration.ofMinutes(durationInMinutes);
    }
}
