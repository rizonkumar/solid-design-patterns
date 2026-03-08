package SOLID.DIP.GoodCode;

public class NotificationService {
    private NotificationChannel notificationChannel;

    public NotificationService(NotificationChannel channel) {
        this.notificationChannel = channel;
    }

    public void notify(String msg) {
        notificationChannel.send(msg);
    }
}
