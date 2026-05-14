package locker;

public class AccountLockerPolicy {
    // Number of days of free storage
    final int freePeriodDays;
    // Maximum number of days a package can be stored
    final int maximumPeriodDays;

    // Creates a new locker policy with specified free and maximum periods
    public AccountLockerPolicy(int freePeriodDays, int maximumPeriodDays) {
        this.freePeriodDays = freePeriodDays;
        this.maximumPeriodDays = maximumPeriodDays;
    }

    public int getFreePeriodDays() {
        return freePeriodDays;
    }

    public int getMaximumPeriodDays() {
        return maximumPeriodDays;
    }
}
