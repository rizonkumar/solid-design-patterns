package locker;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Locker {
    // Size of the locker
    private final LockerSize size;
    // Currently stored package
    private ShippingPackage currentPackage;
    // Date when the current package was assigned
    private Date assignmentDate;
    // Access code for retrieving the package
    private String accessCode;

    public Locker(LockerSize size) {
        this.size = size;
    }

    // Assigns a package to this locker and generates an access code
    public void assignPackage(ShippingPackage pkg, Date date) {
        this.currentPackage = pkg;
        this.assignmentDate = date;
        this.accessCode = generateAccessCode();
    }

    private String generateAccessCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    // Releases the locker by removing the current package and its details
    public void releaseLocker() {
        this.currentPackage = null;
        this.assignmentDate = null;
        this.accessCode = null;
    }

    // Calculates storage charges based on usage duration and policy
    public BigDecimal calculateStorageCharges() {
        if (currentPackage == null || assignmentDate == null) {
            return BigDecimal.ZERO;
        }

        AccountLockerPolicy policy = currentPackage.getUser().getLockerPolicy();
        long totalDaysUsed =
                (new Date().getTime() - assignmentDate.getTime()) / (1000 * 60 * 60 * 24);

        // Check if exceeds maximum period
        if (totalDaysUsed > policy.getMaximumPeriodDays()) {
            currentPackage.updateShippingStatus(ShippingStatus.EXPIRED);
            throw new MaximumStoragePeriodExceededException(
                    "Package has exceeded maximum allowed storage period of "
                            + policy.getMaximumPeriodDays()
                            + " days");
        }

        // Calculate chargeable days (excluding free period)
        long chargeableDays = Math.max(0, totalDaysUsed - policy.getFreePeriodDays());
        return size.dailyCharge.multiply(new BigDecimal(chargeableDays));
    }

    // Checks if the locker is available for new packages
    public boolean isAvailable() {
        return currentPackage == null;
    }

    // Verifies if the provided access code matches the locker's code
    public boolean checkAccessCode(String code) {
        return this.accessCode != null && accessCode.equals(code);
    }

    public String getAccessCode() {
        return accessCode;
    }

    public ShippingPackage getPackage() {
        return currentPackage;
    }
}
