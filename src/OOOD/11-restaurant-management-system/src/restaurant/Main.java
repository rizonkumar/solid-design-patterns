package restaurant;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Restaurant Management System ===");

        // Setup the Menu
        Menu menu = new Menu();
        MenuItem steak = new MenuItem("Steak", "Grilled Steak", new BigDecimal("35.00"), MenuItem.Category.MAIN);
        MenuItem salad = new MenuItem("Salad", "Caesar Salad", new BigDecimal("12.50"), MenuItem.Category.APPETIZER);
        MenuItem cake = new MenuItem("Cheesecake", "New York Cheesecake", new BigDecimal("8.00"), MenuItem.Category.DESSERT);

        menu.addItem(steak);
        menu.addItem(salad);
        menu.addItem(cake);

        // Setup the Layout (Tables of capacities 2, 4, 6)
        Layout layout = new Layout(Arrays.asList(2, 4, 4, 6));

        // Create the Restaurant facade
        Restaurant restaurant = new Restaurant("Gourmet Haven", menu, layout);

        System.out.println("\n--- Booking a Scheduled Reservation ---");
        LocalDateTime reservationTime = LocalDateTime.now().plusDays(1).withHour(19).withMinute(0).withSecond(0).withNano(0);
        Reservation scheduledRes = restaurant.createScheduledReservation("Alice Smith", 4, reservationTime);
        System.out.println("Reservation booked for " + scheduledRes.getPartyName() + " at " + scheduledRes.getTime() + " on Table " + scheduledRes.getAssignedTable().getTableId());

        System.out.println("\n--- Creating a Walk-In Reservation ---");
        Reservation walkInRes = restaurant.createWalkInReservation("Bob Jones", 2);
        System.out.println("Walk-in seated: " + walkInRes.getPartyName() + " on Table " + walkInRes.getAssignedTable().getTableId());

        System.out.println("\n--- Placing Orders for Walk-In ---");
        Table bobTable = walkInRes.getAssignedTable();
        restaurant.orderItem(bobTable, steak);
        restaurant.orderItem(bobTable, salad);
        restaurant.orderItem(bobTable, cake);

        // Check the status
        System.out.println("Orders placed and sent to kitchen.");
        
        System.out.println("\n--- Delivering Orders ---");
        restaurant.deliverItem(bobTable, steak);
        restaurant.deliverItem(bobTable, salad);
        System.out.println("Steak and Salad delivered to table.");

        System.out.println("\n--- Billing ---");
        BigDecimal totalBill = restaurant.calculateTableBill(bobTable);
        System.out.println("Total bill for " + walkInRes.getPartyName() + "'s table: $" + totalBill);
    }
}
