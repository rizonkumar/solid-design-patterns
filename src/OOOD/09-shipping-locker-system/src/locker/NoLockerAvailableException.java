package locker;

public class NoLockerAvailableException extends RuntimeException {
    public NoLockerAvailableException(String message) {
        super(message);
    }
}
