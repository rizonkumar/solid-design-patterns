package locker;

public class LockerFactory {
    // Creates a new locker of the specified size
    public static Locker createLocker(LockerSize size) {
        switch (size) {
            case SMALL: return new Locker(LockerSize.SMALL);
            case MEDIUM: return new Locker(LockerSize.MEDIUM);
            case LARGE: return new Locker(LockerSize.LARGE);
            case XLARGE: return new Locker(LockerSize.XLARGE);
            default: throw new IllegalArgumentException("Unknown locker size");
        }
    }
}
