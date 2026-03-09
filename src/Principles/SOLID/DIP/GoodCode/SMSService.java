package SOLID.DIP.GoodCode;

public class SMSService implements NotificationChannel {

    @Override
    public void send(String msg) {
        System.out.println("Sending SMS : " + msg);
    }
}
