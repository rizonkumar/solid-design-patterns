package DesignPatterns.StructuralPatterns.FacadeDesignPattern.Before;

// Subsystem 1: Payment handling
class PaymentService {
    public void makePayment(String accountId, double amount) {
        System.out.println("Payment of ₹" + amount + " successful for account " + accountId);
    }
}

// Subsystem 2: Seat management
class SeatReservationService {
    public void reserveSeat(String movieId, String seatNumber) {
        System.out.println("Seat " + seatNumber + " reserved for movie " + movieId);
    }
}

// Subsystem 3: Communications
class NotificationService {
    public void sendBookingConfirmation(String userEmail) {
        System.out.println("Booking confirmation sent to " + userEmail);
    }
}

// Subsystem 4: Rewards management
class LoyaltyPointsService {
    public void addPoints(String accountId, int points) {
        System.out.println(points + " loyalty points added to account " + accountId);
    }
}

// Subsystem 5: Ticket generation
class TicketService {
    public void generateTicket(String movieId, String seatNumber) {
        System.out.println("Ticket generated for movie " + movieId + ", Seat: " + seatNumber);
    }
}

/**
 * PROBLEM: The Client (Main) is tightly coupled to FIVE different services.
 */
class Main {
    public static void main(String[] args) {
        // ISSUE 1: Client "Over-knowledge"
        // The client must know the exact order of operations and which service does what.
        
        // ISSUE 2: Tight Coupling
        // If the LoyaltyPointsService changes its method signature, this client code breaks.
        
        // ISSUE 3: Lack of Reusability
        // To book a ticket from a Mobile App or a Web Portal, you'd have to 
        // duplicate this 10-line sequence everywhere.

        PaymentService paymentService = new PaymentService();
        paymentService.makePayment("user123", 500);

        SeatReservationService seatReservationService = new SeatReservationService();
        seatReservationService.reserveSeat("movie456", "A10");

        NotificationService notificationService = new NotificationService();
        notificationService.sendBookingConfirmation("user@example.com");

        LoyaltyPointsService loyaltyPointsService = new LoyaltyPointsService();
        loyaltyPointsService.addPoints("user123", 50);

        TicketService ticketService = new TicketService();
        ticketService.generateTicket("movie456", "A10");
    }
}