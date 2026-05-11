package tictactoe;

import java.util.ArrayDeque;

public class MoveHistory {
    // Stack-like structure to store moves in chronological order
    private final ArrayDeque<Move> history = new ArrayDeque<>();

    // Adds a new move to the history stack
    public void recordMove(Move move) {
        history.push(move);
    }

    // Removes and returns the most recent move from the history
    public Move undoMove() {
        return history.pop();
    }

    // Clears all moves from the history
    public void clearHistory() {
        history.clear();
    }
}
