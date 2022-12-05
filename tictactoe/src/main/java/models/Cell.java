package models;

public class Cell {
    private int row;
    private int col;
    private Player player;

    public Cell(int r, int c) {
        this.row = r;
        this.col = c;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
