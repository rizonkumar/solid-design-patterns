package blackjack;

public class DealerDecisionLogic implements PlayerDecisionLogic {
    @Override
    public Action decideAction(Hand hand) {
        return hand.getBestValue() < 17 ? Action.HIT : Action.STAND;
    }
}
