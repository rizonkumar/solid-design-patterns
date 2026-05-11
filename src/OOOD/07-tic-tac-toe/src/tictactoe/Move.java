package tictactoe;

public class Move {
    private final int colIndex;
    private final int rowIndex;
    private final Player player;

    public Move(int colIndex, int rowIndex, Player player) {
        this.colIndex = colIndex;
        this.rowIndex = rowIndex;
        this.player = player;
    }

    public int getColIndex() {
        return colIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public Player getPlayer() {
        return player;
    }
}
