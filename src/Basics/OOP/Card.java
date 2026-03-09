package Basics.OOP;

abstract public class Card implements PaymentMethod {
    private String cardNo;
    private String userName;

    public Card(String cardNo, String name) {
        this.cardNo = cardNo;
        this.userName = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public String getUserName() {
        return userName;
    }
}
