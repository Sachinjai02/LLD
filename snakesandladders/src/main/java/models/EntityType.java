package models;

public enum EntityType {
    SNAKE("\033[0;31m"),
    LADDER("\033[0;34m"),
    ;

    public String getColor() {
        return color;
    }

    private String color;
    EntityType(String color) {
        this.color = color;
    }
}
