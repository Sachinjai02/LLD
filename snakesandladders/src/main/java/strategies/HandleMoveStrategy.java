package strategies;

import models.Button;
import models.Game;
import models.Player;

public interface HandleMoveStrategy {
    void processMove(Game game, Player player, Button button, int diceValue);
}
