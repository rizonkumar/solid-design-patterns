package blackjack;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Blackjack Simulator ===");
        
        Player alice = new RealPlayer("Alice", 100);
        Player bob = new RealPlayer("Bob", 200);
        
        BlackJackGame game = new BlackJackGame(Arrays.asList(alice, bob));
        
        // Place Bets
        game.bet(alice, 10);
        game.bet(bob, 50);
        
        // Deal Initial Cards
        game.dealInitialCards();
        
        System.out.println("Alice Hand: " + alice.getHand().getCards());
        System.out.println("Bob Hand: " + bob.getHand().getCards());
        
        // Run Game loop (Player Strategy logic taking turns)
        while (game.getGamePhase() != GamePhase.END) {
            game.playNextTurn();
        }
    }
}
