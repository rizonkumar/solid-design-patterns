package tictactoe;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class Board {
    // 3x3 grid to store player moves
    private final Player[][] grid = new Player[3][3];

    // Updates the board with a player's move at the specified position
    public void updateBoard(int colIndex, int rowIndex, Player player) {
        grid[colIndex][rowIndex] = player; // Allow null to clear position on undo
    }

    // Checks for a winner by examining rows, columns, and diagonals
    public Optional<Player> getWinner() {
        // Check rows for three in a row
        for (int i = 0; i < grid.length; i++) {
            Player first = grid[i][0];
            if (first != null && Arrays.stream(grid[i]).allMatch(p -> p == first)) {
                return Optional.of(first);
            }
        }

        // Check columns for three in a column
        for (int j = 0; j < grid[0].length; j++) {
            final Player first = grid[0][j];
            int finalJ = j; // streams require a final object
            if (first != null && Arrays.stream(grid).allMatch(row -> row[finalJ] == first)) {
                return Optional.of(first);
            }
        }

        // Check main diagonal (top-left to bottom-right)
        Player topLeft = grid[0][0];
        if (topLeft != null
                && IntStream.range(0, grid.length).allMatch(i -> grid[i][i] == topLeft)) {
            return Optional.of(topLeft);
        }

        // Check anti-diagonal (top-right to bottom-left)
        Player topRight = grid[0][grid[0].length - 1];
        if (topRight != null
                && IntStream.range(0, grid.length)
                        .allMatch(i -> grid[i][grid[0].length - 1 - i] == topRight)) {
            return Optional.of(topRight);
        }

        // No winner found
        return Optional.empty();
    }

    // Checks if all positions on the board are filled
    public boolean isFull() {
        return Arrays.stream(grid).flatMap(Arrays::stream).noneMatch(Objects::isNull);
    }

    // Resets the board by clearing all positions
    public void reset() {
        for (Player[] players : grid) {
            Arrays.fill(players, null);
        }
    }

    // Returns the player at the specified position, or null if empty
    public Player getPlayerAt(int colIndex, int rowIndex) {
        return grid[colIndex][rowIndex];
    }
    
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                Player p = grid[i][j];
                char symbol = p == null ? ' ' : p.getSymbol();
                System.out.print(symbol + " | ");
            }
            System.out.println("\n-------------");
        }
    }
}
