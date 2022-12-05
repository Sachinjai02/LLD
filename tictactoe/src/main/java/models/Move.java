package models;

public class Move {
    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    private Cell cell;

    public Move(Cell cell, Player player) {
        this.cell = cell;
        this.cell.setPlayer(player);
    }

}
