package locker;

public class MaximumStoragePeriodExceededException extends RuntimeException {
    public MaximumStoragePeriodExceededException(String message) {
        super(message);
    }
}
