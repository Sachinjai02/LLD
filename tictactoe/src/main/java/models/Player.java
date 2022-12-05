package models;

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

}
