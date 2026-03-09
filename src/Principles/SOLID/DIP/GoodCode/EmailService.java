package SOLID.DIP.GoodCode;

public class EmailService implements NotificationChannel {

    @Override
    public void send(String msg) {
        System.out.println("Sending Email  : " + msg);
    }
}
