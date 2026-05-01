package booking;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;

public class ScreeningManager {
    private final Map<Movie, List<Screening>> screeningsByMovie;
    private final Map<Screening, List<Ticket>> ticketsByScreening;

    public ScreeningManager() {
        this.screeningsByMovie = new HashMap<>();
        this.ticketsByScreening = new HashMap<>();
    }

    public void addScreening(Movie movie, Screening screening) {
        screeningsByMovie.computeIfAbsent(movie, k -> new ArrayList<>()).add(screening);
    }

    public List<Screening> getScreeningsForMovie(Movie movie) {
        return screeningsByMovie.getOrDefault(movie, new ArrayList<>());
    }

    public void addTicket(Screening screening, Ticket ticket) {
        ticketsByScreening.computeIfAbsent(screening, k -> new ArrayList<>()).add(ticket);
    }

    public List<Ticket> getTicketsForScreening(Screening screening) {
        return ticketsByScreening.getOrDefault(screening, new ArrayList<>());
    }

    public List<Seat> getAvailableSeats(Screening screening) {
        List<Seat> allSeats = screening.getRoom().getLayout().getAllSeats();
        List<Ticket> bookedTickets = getTicketsForScreening(screening);

        List<Seat> availableSeats = new ArrayList<>(allSeats);
        for (Ticket ticket : bookedTickets) {
            availableSeats.remove(ticket.getSeat());
        }
        return availableSeats;
    }

    /**
     * OPTIMISTIC LOCKING IMPLEMENTATION
     * 
     * Optimistic locking assumes that conflicts between transactions (users trying to book the same seat) 
     * are rare. Therefore, it does not lock the seat when a user selects it. Instead, it only checks 
     * if the seat is available right at the exact moment of booking.
     * 
     * Pros: Higher performance and scalability, as it avoids the overhead of managing locks in a database or memory.
     * Cons: If a conflict happens (two users click "buy" at the exact same time for the same seat), one will fail 
     * and receive an error, which can lead to a poor user experience.
     */
    public synchronized Ticket bookSeatOptimistically(Screening screening, Seat seat) {
        // First check if a seat is available (optimistic check right before transaction)
        if (isSeatBooked(screening, seat)) {
            // Conflict detected! The optimistic assumption failed.
            throw new IllegalStateException("Seat is already booked by another user.");
        }

        // Create ticket - at this point, we're optimistically assuming
        // the seat is still available and the transaction can be committed
        BigDecimal price = seat.getPricingStrategy().getPrice();
        Ticket ticket = new Ticket(screening, seat, price);

        // Add to booking system - this effectively "reserves/commits" the seat
        ticketsByScreening.computeIfAbsent(screening, k -> new ArrayList<>()).add(ticket);

        return ticket;
    }

    // Helper method to check if a seat is already booked
    private boolean isSeatBooked(Screening screening, Seat seat) {
        List<Ticket> tickets = getTicketsForScreening(screening);
        return tickets.stream().anyMatch(ticket -> ticket.getSeat().equals(seat));
    }
}
