package blackjack;

public interface PlayerDecisionLogic {
    // Decides the next action for a player based on their hand
    Action decideAction(Hand hand);
}
