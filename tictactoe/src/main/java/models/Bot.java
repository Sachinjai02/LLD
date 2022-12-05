package models;

public class Bot extends Player {
    private DifficultyLevel level;

    public Bot(DifficultyLevel level) {
        this.level = level;
    }

    public DifficultyLevel getLevel() {
        return level;
    }

    public void setLevel(DifficultyLevel level) {
        this.level = level;
    }


}
