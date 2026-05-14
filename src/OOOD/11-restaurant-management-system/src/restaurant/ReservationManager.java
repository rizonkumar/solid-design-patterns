package restaurant;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Manages all reservations for the restaurant and handles table assignments
public class ReservationManager {
    private final Layout layout;
    private final Set<Reservation> reservations = new HashSet<>();

    // Constructor that takes the restaurant's table layout
    public ReservationManager(Layout layout) {
        this.layout = layout;
    }

    // Finds potential time slots for a reservation within the given time range and party size
    public LocalDateTime[] findAvailableTimeSlots(
            LocalDateTime rangeStart, LocalDateTime rangeEnd, int partySize) {
        // checking every hour in the time range
        LocalDateTime current = rangeStart;
        List<LocalDateTime> possibleReservations = new ArrayList<>();
        while (!current.isAfter(rangeEnd)) {
            Table availableTable = layout.findAvailableTable(partySize, current);
            if (availableTable != null) {
                possibleReservations.add(current);
            }
            current = current.plusHours(1);
        }
        return possibleReservations.toArray(new LocalDateTime[0]);
    }

    // Creates a reservation for a specific time, party size and name
    public Reservation createReservation(
            String partyName, int partySize, LocalDateTime desiredTime) {
        desiredTime = desiredTime.truncatedTo(ChronoUnit.HOURS);
        Table table = layout.findAvailableTable(partySize, desiredTime);
        if (table == null) {
            throw new IllegalStateException("No available table found for party size " + partySize + " at " + desiredTime);
        }
        Reservation reservation = new Reservation(partyName, partySize, desiredTime, table);
        table.addReservation(reservation);
        reservations.add(reservation);
        return reservation;
    }

    // Removes an existing reservation
    public void removeReservation(
            String partyName, int partySize, LocalDateTime reservationTime) {
        // Find matching reservation before removing it
        for (Reservation reservation : new HashSet<>(reservations)) {
            if (reservation.getTime().equals(reservationTime)
                    && reservation.getPartySize() == partySize
                    && reservation.getPartyName().equals(partyName)) {
                // Clear the reservation from the table first
                Table table = reservation.getAssignedTable();
                table.removeReservation(reservationTime);
                // Then remove from the reservation collection
                reservations.remove(reservation);
                return;
            }
        }
    }
}
