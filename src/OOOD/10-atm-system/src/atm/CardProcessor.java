package atm;

public interface CardProcessor {
    void setCardNumber(String cardNumber);
    String getCardNumber();
    void ejectCard();
}
