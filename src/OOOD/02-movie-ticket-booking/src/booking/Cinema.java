package booking;

import java.util.List;
import java.util.ArrayList;

public class Cinema {
    private final String name;
    private final String location;
    private final List<Room> rooms;

    public Cinema(String name, String location) {
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public List<Room> getRooms() { return rooms; }

    public void addRoom(Room room) {
        rooms.add(room);
    }
}
