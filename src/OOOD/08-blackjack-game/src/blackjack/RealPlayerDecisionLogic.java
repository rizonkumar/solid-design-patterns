package blackjack;

public class RealPlayerDecisionLogic implements PlayerDecisionLogic {
    @Override
    public Action decideAction(Hand hand) {
        return hand.getBestValue() < 16 ? Action.HIT : Action.STAND;
    }
}
