package models;

import factories.BotPlayingStrategyFactory;
import strategies.playing.BotPlayingStrategy;

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

    @Override
    public Move makeMove(Board board) {
        BotPlayingStrategy strategy = BotPlayingStrategyFactory.getBotPlayingStrategyByDifficultyLevel(this.level);
        return strategy.makeMove(board);
    }
}
