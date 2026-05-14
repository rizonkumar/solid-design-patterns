package locker;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Shipping Locker System Initialization ===");
        
        // Define lockers
        Map<LockerSize, Integer> lockerConfig = new HashMap<>();
        lockerConfig.put(LockerSize.SMALL, 5);
        lockerConfig.put(LockerSize.MEDIUM, 3);
        lockerConfig.put(LockerSize.LARGE, 2);
        
        Site site = new Site(lockerConfig);
        
        // Define accounts
        AccountLockerPolicy policy = new AccountLockerPolicy(3, 7); // 3 days free, 7 max
        Account alice = new Account("A1", "Alice", policy);
        Map<String, Account> accounts = new HashMap<>();
        accounts.put("A1", alice);
        
        LockerManager manager = new LockerManager(site, accounts);
        
        // Setup observer for emails
        EmailNotification emailNotifier = new EmailNotification();
        manager.addObserver(emailNotifier);
        
        // Incoming package
        System.out.println("\n--- Assigning Package ---");
        BasicShippingPackage pkg = new BasicShippingPackage("ORDER-123", alice, new BigDecimal("8.00"), new BigDecimal("8.00"), new BigDecimal("8.00"));
        Locker assignedLocker = manager.assignPackage(pkg, new Date());
        
        System.out.println("Package fits in: " + pkg.getLockerSize());
        
        // Pickup Package
        System.out.println("\n--- Picking Up Package ---");
        manager.pickUpPackage(assignedLocker.getAccessCode());
        
        System.out.println("Alice total usage charges: $" + alice.getUsageCharges());
    }
}
