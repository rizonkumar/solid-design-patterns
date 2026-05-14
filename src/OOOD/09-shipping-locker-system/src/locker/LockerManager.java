package locker;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LockerManager {
    // The site being managed
    private final Site site;
    // Map of account IDs to account objects
    private final Map<String, Account> accounts;
    // Map of access codes to lockers
    private final Map<String, Locker> accessCodeMap = new HashMap<>();
    
    // Observer pattern deep dive
    private final LockerManagerChange eventManager;

    // Creates a new locker manager for a site
    public LockerManager(Site site, Map<String, Account> accounts) {
        this.site = site;
        this.accounts = accounts;
        this.eventManager = new LockerManagerChange(site);
    }
    
    public void addObserver(LockerEventObserver observer) {
        eventManager.addObserver(observer);
    }

    // Assigns a package to an available locker
    public Locker assignPackage(ShippingPackage pkg, Date date) {
        Locker locker = site.placePackage(pkg, date);
        if (locker != null) {
            accessCodeMap.put(locker.getAccessCode(), locker);
            eventManager.notifyObservers("Package assigned to locker. Code: " + locker.getAccessCode(), pkg.getUser());
        }
        return locker;
    }

    // Processes package pickup using an access code
    public Locker pickUpPackage(String accessCode) {
        Locker locker = accessCodeMap.get(accessCode);
        if (locker != null && locker.checkAccessCode(accessCode)) {
            try {
                BigDecimal charge = locker.calculateStorageCharges();
                ShippingPackage pkg = locker.getPackage();
                locker.releaseLocker();
                accessCodeMap.remove(accessCode);
                pkg.getUser().addUsageCharge(charge);
                pkg.updateShippingStatus(ShippingStatus.RETRIEVED);
                eventManager.notifyObservers("Package picked up. Usage charge: $" + charge, pkg.getUser());
                return locker;
            } catch (MaximumStoragePeriodExceededException e) {
                ShippingPackage pkg = locker.getPackage();
                locker.releaseLocker();
                accessCodeMap.remove(accessCode);
                eventManager.notifyObservers("Package EXPIRED and removed by staff.", pkg.getUser());
                return locker;
            }
        }
        return null;
    }
}
