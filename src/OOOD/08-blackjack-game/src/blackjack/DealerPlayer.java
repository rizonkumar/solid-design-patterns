package blackjack;

public class DealerPlayer implements Player {
    private final String name = "Dealer";
    private final Hand hand;
    private final PlayerDecisionLogic decisionLogic;

    public DealerPlayer() {
        this.hand = new Hand();
        this.decisionLogic = new DealerDecisionLogic();
    }

    @Override
    public void bet(int bet) {
        // Dealer doesn't bet
    }

    @Override
    public void loseBet() {
        // Dealer doesn't lose bet in the same way
    }

    @Override
    public void returnBet() {
        // Dealer doesn't return bet
    }

    @Override
    public void payout() {
        // Dealer does not get a payout, so this method only prints the winning hand
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
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBet() {
        return 0;
    }

    // Returns the decision logic for the dealer
    @Override
    public PlayerDecisionLogic getDecisionLogic() {
        return decisionLogic;
    }
}
