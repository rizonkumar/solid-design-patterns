package blackjack;

public class RealPlayer implements Player {
    private final String name;
    private final Hand hand;
    private int bet;
    private int balance;
    private final PlayerDecisionLogic decisionLogic;

    public RealPlayer(String name, int startBalance) {
        this.name = name;
        this.hand = new Hand();
        this.bet = 0;
        this.balance = startBalance;
        this.decisionLogic = new RealPlayerDecisionLogic();
    }

    // Places a bet for the player
    @Override
    public void bet(int bet) {
        if (bet > balance) {
            throw new IllegalArgumentException("Bet is greater than balance");
        }
        this.bet = bet;
        this.balance -= bet;
    }

    // Handles the player losing a bet
    @Override
    public void loseBet() {
        this.bet = 0;
    }

    // Handles returning the player's bet
    @Override
    public void returnBet() {
        this.balance += bet;
        this.bet = 0;
    }

    // Handles the player winning a payout
    @Override
    public void payout() {
        this.balance += bet * 2; // Return bet plus equal amount
        this.bet = 0;
    }

    @Override
    public boolean isBust() {
        return hand.isBust();
    }

    @Override
    public Hand getHand() {
        return hand;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBet() {
        return bet;
    }

    // Returns the decision logic for the player
    @Override
    public PlayerDecisionLogic getDecisionLogic() {
        return decisionLogic;
    }
}
