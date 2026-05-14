package locker;

public class EmailNotification implements LockerEventObserver, NotificationInterface {
    @Override
    public void update(String message, Account account) {
        sendNotification(message, account);
    }
    
    @Override
    public void sendNotification(String message, Account user) {
        System.out.println("[EMAIL to " + user.getOwnerName() + "]: " + message);
    }
}
