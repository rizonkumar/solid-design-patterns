package tictactoe;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Tic-Tac-Toe Initialization ===");
        
        Player player1 = new Player("Alice", 'X');
        Player player2 = new Player("Bob", 'O');
        
        Game game = new Game(player1, player2);
        
        System.out.println("Starting game between " + player1.getName() + " and " + player2.getName());
        game.getBoard().printBoard();
        
        // Move 1: Alice (0, 0)
        game.makeMove(0, 0, player1);
        System.out.println("Alice moved to (0,0):");
        game.getBoard().printBoard();
        
        // Move 2: Bob (1, 1)
        game.makeMove(1, 1, player2);
        System.out.println("Bob moved to (1,1):");
        game.getBoard().printBoard();
        
        // Undo Move 2: Bob
        System.out.println("Bob undoes his move!");
        game.undoMove();
        game.getBoard().printBoard();
        
        // Move 2 (Remake): Bob (0, 1)
        game.makeMove(0, 1, player2);
        System.out.println("Bob moved to (0,1):");
        game.getBoard().printBoard();
        
        // Move 3: Alice (1, 0)
        game.makeMove(1, 0, player1);
        System.out.println("Alice moved to (1,0):");
        game.getBoard().printBoard();
        
        // Move 4: Bob (2, 2)
        game.makeMove(2, 2, player2);
        System.out.println("Bob moved to (2,2):");
        game.getBoard().printBoard();
        
        // Move 5: Alice (2, 0) - Winning move for Alice
        game.makeMove(2, 0, player1);
        System.out.println("Alice moved to (2,0):");
        game.getBoard().printBoard();
        
        System.out.println("Game Status: " + game.getGameStatus());
        if (game.getGameStatus() == GameCondition.ENDED) {
            System.out.println("Winner: " + game.getBoard().getWinner().map(Player::getName).orElse("Draw"));
        }
        
        System.out.println("\nScores:");
        System.out.println("Alice: " + game.getScoreTracker().getScore(player1));
        System.out.println("Bob: " + game.getScoreTracker().getScore(player2));
    }
}
