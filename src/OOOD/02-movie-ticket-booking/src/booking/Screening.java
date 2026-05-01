package booking;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.UUID;

public class Screening {
    private final String id;
    private final Movie movie;
    private final Room room;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Screening(Movie movie, Room room, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = UUID.randomUUID().toString();
        this.movie = movie;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() { return id; }
    public Movie getMovie() { return movie; }
    public Room getRoom() { return room; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }
}
