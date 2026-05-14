package locker;

public interface LockerEventObserver {
    // Updates the observer with a message and the affected account
    void update(String message, Account account);
}
