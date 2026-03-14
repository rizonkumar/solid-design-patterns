package DesignPatterns.StructuralPatterns.FacadeDesignPattern.After;

/**
 * SUBSYSTEM CLASSES
 * These classes represent the internal complexities of the system.
 * They are still accessible but the client no longer needs to manage them directly.
 */
class PaymentService {

    public void makePayment(String accountId, double amount) {
        System.out.println(
            "Payment of ₹" + amount + " successful for account " + accountId
        );
    }
}

class SeatReservationService {

    public void reserveSeat(String movieId, String seatNumber) {
        System.out.println(
            "Seat " + seatNumber + " reserved for movie " + movieId
        );
    }
}

class NotificationService {

    public void sendBookingConfirmation(String userEmail) {
        System.out.println("Booking confirmation sent to " + userEmail);
    }
}

class LoyaltyPointsService {

    public void addPoints(String accountId, int points) {
        System.out.println(
            points + " loyalty points added to account " + accountId
        );
    }
}

class TicketService {

    public void generateTicket(String movieId, String seatNumber) {
        System.out.println(
            "Ticket generated for movie " + movieId + ", Seat: " + seatNumber
        );
    }
}

/**
 * THE FACADE
 * This class provides a simplified, higher-level interface to a set of
 * interfaces in the subsystem. It delegates client requests to appropriate objects.
 */
class MovieBookingFacade {

    private PaymentService paymentService;
    private SeatReservationService seatReservationService;
    private NotificationService notificationService;
    private LoyaltyPointsService loyaltyPointsService;
    private TicketService ticketService;

    // Encapsulation: The Facade manages the lifecycles of the internal services.
    public MovieBookingFacade() {
        this.paymentService = new PaymentService();
        this.seatReservationService = new SeatReservationService();
        this.notificationService = new NotificationService();
        this.loyaltyPointsService = new LoyaltyPointsService();
        this.ticketService = new TicketService();
    }

    /**
     * UNIFIED INTERFACE
     * This method orchestrates the complex workflow of booking a ticket.
     * If the order of operations changes, we only update it here.
     */
    public void bookMovieTicket(
        String accountId,
        String movieId,
        String seatNumber,
        String userEmail,
        double amount
    ) {
        // Step 1: Financial Transaction
        paymentService.makePayment(accountId, amount);

        // Step 2: Resource Allocation
        seatReservationService.reserveSeat(movieId, seatNumber);

        // Step 3: Fulfillment
        ticketService.generateTicket(movieId, seatNumber);

        // Step 4: Engagement/Rewards
        loyaltyPointsService.addPoints(accountId, 50);

        // Step 5: Communication
        notificationService.sendBookingConfirmation(userEmail);

        System.out.println("Movie ticket booking completed successfully!");
    }
}

/**
 * THE CLIENT
 * Now follows the "Principle of Least Knowledge."
 * It only knows about the Facade, not the individual subsystems.
 */
class Main {

    public static void main(String[] args) {
        // High-level intent: "I want to book a ticket"
        MovieBookingFacade movieBookingFacade = new MovieBookingFacade();

        // One call replaces the 10-line ritual from the "Before" code.
        movieBookingFacade.bookMovieTicket(
            "user123",
            "movie456",
            "A10",
            "user@example.com",
            500
        );
    }
}
