package factories;

import models.DifficultyLevel;
import strategies.playing.BotEasyPlayingStrategy;
import strategies.playing.BotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyByDifficultyLevel(DifficultyLevel difficultyLevel) {
        return new BotEasyPlayingStrategy();
    }
}
