package blackjack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private final Deck deck = new Deck();
    private final List<Player> players = new ArrayList<>();
    protected final Player dealer = new DealerPlayer();
    private Player currentPlayer = null;

    // Tracks the current status of each player's turn (e.g., HIT or STAND)
    Map<Player, Action> playerTurnStatusMap = new HashMap<>();
    GamePhase currentPhase = GamePhase.STARTED;

    public BlackJackGame(List<Player> players) {
        for (Player player : players) {
            if (player == null) throw new IllegalArgumentException();
            this.players.add(player);
            this.playerTurnStatusMap.put(player, null);
        }
        this.playerTurnStatusMap.put(dealer, null);
        deck.shuffle(); // Shuffle the deck when game starts
    }

    // Find the next player who can take an action
    public Player getNextEligiblePlayer() {
        // No current player: find first eligible player from the start
        if (currentPlayer == null) {
            for (Player player : players) {
                if (!Action.STAND.equals(playerTurnStatusMap.get(player)) && !player.isBust()) {
                    currentPlayer = player;
                    return currentPlayer;
                }
            }
            // Instead of calling dealerTurn(), check if the dealer can act
            if (!Action.STAND.equals(playerTurnStatusMap.get(dealer)) && !dealer.isBust()) {
                currentPlayer = dealer;
                return dealer;
            }
        } else {
            int currentIndex = players.indexOf(currentPlayer);
            if (currentIndex != -1) {
                for (int i = currentIndex + 1; i < players.size(); i++) {
                    Player player = players.get(i);
                    if (!Action.STAND.equals(playerTurnStatusMap.get(player)) && !player.isBust()) {
                        currentPlayer = player;
                        return currentPlayer;
                    }
                }
            }
            // If all players are done, check if the dealer can act
            if (currentPlayer != dealer && !Action.STAND.equals(playerTurnStatusMap.get(dealer)) && !dealer.isBust()) {
                currentPlayer = dealer;
                return dealer;
            }
        }
        return null; // All turns are complete, including the dealer's
    }

    // Executes the next turn by acting for the next player or dealer.
    public void playNextTurn() {
        Player nextPlayer = getNextEligiblePlayer();
        if (nextPlayer != null) {
            performPlayerAction(nextPlayer);
            
            // Re-evaluate game end condition after action
            checkGameEndCondition();
        }
    }

    // Performs the action decided by the player's decision logic (hit or stand).
    public void performPlayerAction(Player player) {
        Action action = player.getDecisionLogic().decideAction(player.getHand());
        System.out.println(player.getName() + " decides to " + action);
        if (action == Action.HIT) {
            hit(player);
            System.out.println(player.getName() + " drew a card. Hand: " + player.getHand().getCards());
            if (player.isBust()) {
                System.out.println(player.getName() + " BUSTS!");
            }
        } else if (action == Action.STAND) {
            stand(player);
        }
    }

    public void startNewRound() {
        deck.reset();
        deck.shuffle();
        for (Player player : playerTurnStatusMap.keySet()) {
            player.getHand().clear(); // Clear player's hand
        }
        // Reset all turn statuses to null
        for (Map.Entry<Player, Action> entry : playerTurnStatusMap.entrySet()) {
            entry.setValue(null);
        }
        currentPlayer = null; // Reset current player
        currentPhase = GamePhase.STARTED;
    }

    public void dealInitialCards() {
        if (!GamePhase.BET_PLACED.equals(currentPhase)) {
            throw new IllegalStateException("All players must bet before dealing");
        }
        // Deal first card to each real player in order
        for (Player player : players) {
            player.getHand().addCard(deck.draw());
        }
        // Deal first card to dealer
        dealer.getHand().addCard(deck.draw());
        // Deal second card to each real player in order
        for (Player player : players) {
            player.getHand().addCard(deck.draw());
        }
        // Deal second card to dealer
        dealer.getHand().addCard(deck.draw());
        currentPhase = GamePhase.INITIAL_CARD_DRAWN;
        
        System.out.println("Initial cards dealt.");
    }

    public void bet(Player player, int bet) {
        if (!GamePhase.STARTED.equals(currentPhase)) {
            throw new IllegalStateException("Bets must be placed at the start of the round");
        }
        player.bet(bet);
        System.out.println(player.getName() + " bets $" + bet);
        // Transition to BET_PLACED once all players have bet
        if (players.stream()
                .filter(p -> !(p instanceof DealerPlayer))
                .allMatch(p -> p.getBet() > 0)) {
            currentPhase = GamePhase.BET_PLACED;
        }
    }

    public void hit(Player player) {
        if (Action.STAND.equals(playerTurnStatusMap.get(player))) {
            throw new IllegalStateException("Player has already stood");
        }
        if (player.isBust()) {
            throw new IllegalStateException("Player is already bust");
        }

        Card drawnCard = deck.draw();
        player.getHand().addCard(drawnCard);
        
        // After hitting, keep their state as null to hit again or if they bust update it.
        // If they didn't bust they are still eligible to hit. We don't mark HIT, just let loop continue.
    }

    public void stand(Player player) {
        if (Action.STAND.equals(playerTurnStatusMap.get(player))) {
            throw new IllegalStateException("Player has already stood");
        }
        if (player.isBust()) {
            throw new IllegalStateException("Player is already bust");
        }
        playerTurnStatusMap.put(player, Action.STAND);
    }

    // Checks if the game has ended (all players done), then resolves bets by comparing each
    // player's hand to the dealer's.
    private void checkGameEndCondition() {
        boolean allPlayersDone = true;
        for (Player p : playerTurnStatusMap.keySet()) {
            if (!Action.STAND.equals(playerTurnStatusMap.get(p)) && !p.isBust()) {
                allPlayersDone = false;
                break;
            }
        }
        
        if (!allPlayersDone) {
            return;
        }

        int dealerValue = dealer.getHand().getBestValue();
        boolean dealerBusts = dealer.isBust();
        
        System.out.println("=== Round Over ===");
        System.out.println("Dealer Hand: " + dealer.getHand().getCards() + " (Value: " + dealerValue + ")" + (dealerBusts ? " BUST" : ""));

        for (Player player : players) {
            if (player.isBust()) {
                System.out.println(player.getName() + " busted. Lost bet.");
                player.loseBet();
            } else {
                int playerValue = player.getHand().getBestValue();
                System.out.println(player.getName() + " Hand: " + player.getHand().getCards() + " (Value: " + playerValue + ")");
                
                if (dealerBusts || playerValue > dealerValue) {
                    System.out.println(player.getName() + " Wins!");
                    player.payout();
                } else if (playerValue == dealerValue) {
                    System.out.println(player.getName() + " Pushes (Tie).");
                    player.returnBet();
                } else {
                    System.out.println(player.getName() + " Loses.");
                    player.loseBet();
                }
            }
            System.out.println(player.getName() + " new balance: $" + player.getBalance());
        }
        currentPhase = GamePhase.END;
    }
    
    public GamePhase getGamePhase() {
        return currentPhase;
    }
}
