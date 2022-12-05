package factories;

import models.*;

public class PlayerFactory {
    public static Player createHumanPlayer(String name, Character symbol) {
        return new Player(name, symbol, PlayerType.HUMAN);
    }

    public static Bot createBot(String name, Character symbol, String difficultyLevelName) {
        DifficultyLevel difficultyLevel = DifficultyLevelFactory.getDifficultyLevelByName(difficultyLevelName);
        Bot bot =  new Bot(difficultyLevel);
        bot.setName(name);
        bot.setSymbol(new Symbol(symbol));
        bot.setType(PlayerType.BOT);

        return bot;
    }
}
