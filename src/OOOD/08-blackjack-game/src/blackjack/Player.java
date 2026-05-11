package blackjack;

public interface Player {
    void bet(int bet);

    void loseBet();

    void returnBet();

    void payout();

    boolean isBust();

    Hand getHand();

    int getBalance();

    String getName();

    int getBet();
    
    PlayerDecisionLogic getDecisionLogic();
}
