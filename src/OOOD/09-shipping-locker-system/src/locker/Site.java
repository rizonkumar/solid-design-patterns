package locker;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Site {
    // Map of locker sizes to sets of lockers of that size
    final Map<LockerSize, Set<Locker>> lockers = new HashMap<>();

    // Creates a new site with specified number of lockers for each size
    public Site(Map<LockerSize, Integer> lockerConfig) {
        for (Map.Entry<LockerSize, Integer> entry : lockerConfig.entrySet()) {
            Set<Locker> lockerSet = new HashSet<>();
            for (int i = 0; i < entry.getValue(); i++) {
                // Using factory method as requested in deep dive
                lockerSet.add(LockerFactory.createLocker(entry.getKey()));
            }
            this.lockers.put(entry.getKey(), lockerSet);
        }
    }

    // Finds an available locker of the specified size
    public Locker findAvailableLocker(LockerSize size) {
        if (lockers.containsKey(size)) {
            for (Locker locker : lockers.get(size)) {
                if (locker.isAvailable()) {
                    return locker;
                }
            }
        }
        return null;
    }

    // Places a package in an available locker of appropriate size
    public Locker placePackage(ShippingPackage pkg, Date date) {
        // Determine the smallest locker size that can fit this package
        LockerSize size = pkg.getLockerSize();
        Locker locker = findAvailableLocker(size);
        if (locker != null) {
            locker.assignPackage(pkg, date);
            pkg.updateShippingStatus(ShippingStatus.IN_LOCKER);
            return locker;
        }
        throw new NoLockerAvailableException(
                "No locker of size " + size + " is currently available");
    }
}
