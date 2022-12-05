package models;

import java.util.Scanner;

public class Player {

    public Player(String name, char symbol, PlayerType type) {
        this.name = name;
        this.symbol = new Symbol(symbol);
        this.type = type;
    }

    public Player() {

    }
    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    private Symbol symbol;
    private String name;
    private PlayerType type;

    public Move makeMove(Board board) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter row (0 based)");
        int row = scanner.nextInt();
        System.out.println("Enter col (0 based)");
        int col = scanner.nextInt();
        return new Move(row, col);

    }
}
