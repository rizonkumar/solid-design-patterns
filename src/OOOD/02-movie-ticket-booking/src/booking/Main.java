package booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MovieBookingSystem system = new MovieBookingSystem();

        // 1. Create a Movie
        Movie inception = new Movie("Inception", "Sci-Fi", 148);
        system.addMovie(inception);

        // 2. Create a Layout and Room
        Layout layout = new Layout(5, 5); // 25 seats
        Room room1 = new Room("Room 1", layout);

        // 3. Create a Cinema
        Cinema myCinema = new Cinema("Grand Cinema", "Downtown");
        myCinema.addRoom(room1);
        system.addCinema(myCinema);

        // 4. Create a Screening
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusMinutes(inception.getDurationInMinutes());
        Screening screening = new Screening(inception, room1, startTime, endTime);
        system.addScreening(inception, screening);

        System.out.println("--- Movie Ticket Booking System ---");
        System.out.println("Movie: " + inception.getTitle() + " | Duration: " + inception.getDurationInMinutes() + " mins");
        System.out.println("Screening Time: " + startTime + " to " + endTime);

        // 5. Book a Ticket
        List<Seat> availableSeats = system.getAvailableSeats(screening);
        if (!availableSeats.isEmpty()) {
            Seat selectedSeat = availableSeats.get(0);
            
            // Optionally change pricing strategy for the seat
            selectedSeat.setPricingStrategy(new VIPRate(new BigDecimal("25.00")));

            System.out.println("\nBooking Seat: " + selectedSeat.getSeatNumber() + " with rate: $" + selectedSeat.getPricingStrategy().getPrice());
            system.bookTicket(screening, selectedSeat);

            // 6. Create an Order
            Order order = new Order(LocalDateTime.now());
            // Get the ticket we just booked
            Ticket bookedTicket = system.getTicketsForScreening(screening).get(0);
            order.addTicket(bookedTicket);

            System.out.println("\nOrder details:");
            System.out.println("Total Price: $" + order.calculateTotalPrice());
            System.out.println("Seats Available after booking: " + system.getAvailableSeats(screening).size());
        }
    }
}
