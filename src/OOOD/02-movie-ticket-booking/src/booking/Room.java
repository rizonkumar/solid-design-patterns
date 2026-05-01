package booking;

public class Room {
    private final String roomNumber;
    private final Layout layout;

    public Room(String roomNumber, Layout layout) {
        this.roomNumber = roomNumber;
        this.layout = layout;
    }

    public String getRoomNumber() { return roomNumber; }
    public Layout getLayout() { return layout; }
}
